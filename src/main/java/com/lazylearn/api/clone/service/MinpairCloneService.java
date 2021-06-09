package com.lazylearn.api.clone.service;

import com.lazylearn.api.entity.Minpair;
import com.lazylearn.api.entity.MinpairFile;
import com.lazylearn.api.entity.User;
import com.lazylearn.api.indto.EncodedFile;
import com.lazylearn.api.indto.minpair.MinpairCreateIn;
import com.lazylearn.api.repo.MinpairFileRepo;
import com.lazylearn.api.repo.MinpairRepo;
import com.lazylearn.api.repo.UserRepo;
import com.lazylearn.api.service.FileService;
import com.lazylearn.api.service.MinpairFileService;
import com.lazylearn.api.service.MinpairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author 4nha
 * Date: 2020-05-02
 */
@Service
@Slf4j
public class MinpairCloneService {

    @Autowired
    private MinpairService minpairService;
    @Autowired
    private MinpairFileService minpairFileService;
    @Autowired
    private MinpairRepo minpairRepo;
    @Autowired
    private MinpairFileRepo minpairFileRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FileService fileService;


    private MinpairCreateIn createMinpairDto(String oldMinpairId) throws IOException {
        Minpair minpair = minpairRepo.findOne(oldMinpairId);

        MinpairCreateIn minpairCreateIn = new MinpairCreateIn();
        minpairCreateIn.setWord1(minpair.getWord1());
        minpairCreateIn.setWord2(minpair.getWord2());
        minpairCreateIn.setPhonetic1(minpair.getPhonetic1());
        minpairCreateIn.setPhonetic2(minpair.getPhonetic2());
        minpairCreateIn.setLanguage(minpair.getLanguage());

        List<MinpairFile> minpairFiles1 = minpairFileRepo.findAllByMinpairIdAndSide(oldMinpairId, 1);
        List<MinpairFile> minpairFiles2 = minpairFileRepo.findAllByMinpairIdAndSide(oldMinpairId, 2);

        List<EncodedFile> files1 = new ArrayList<>();
        List<EncodedFile> files2 = new ArrayList<>();

        for (MinpairFile minpairFile : minpairFiles1) {
            files1.add(fileService.readFileFromPath(minpairFile.getAudioPath()));
        }

        for (MinpairFile minpairFile : minpairFiles2) {
            files2.add(fileService.readFileFromPath(minpairFile.getAudioPath()));
        }

        minpairCreateIn.setAudioFiles1(files1);
        minpairCreateIn.setAudioFiles2(files2);

        return minpairCreateIn;
    }

    @Transactional
    public void cloneMinpair(String oldMinpairId, String userId) throws Exception {
        Minpair oldMinpair = minpairRepo.findOne(oldMinpairId);
        if (isBlank(oldMinpair.getCloneableid())) {
            throw new RuntimeException("Cannot clone because this minpair does not have cloneableid");
        }
        if (minpairRepo.countByUser_IdAndCloneableid(userId, oldMinpair.getCloneableid()) > 0) {
            log.info("Skip because already imported");
            return;
        }

        Minpair newMinpair = minpairService.create(createMinpairDto(oldMinpairId), userId);
        newMinpair.setCloneableid(oldMinpair.getCloneableid());
        minpairRepo.save(newMinpair);
    }

    @Transactional
    public void cloneMinpair(String oldMinpairId) {
        for (User user : userRepo.findAll()) {
            try {
                cloneMinpair(oldMinpairId, user.getUserId());
            } catch (Exception e) {
                log.error("Cannot clone for user: {}", user.getEmail());
                log.error("Cannot clone for user", e);
            }
        }
    }
}
