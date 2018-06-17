package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.Minpair;
import com.mcflythekid.lazylearncore.indto.minpair.MinpairCreateIn;
import com.mcflythekid.lazylearncore.indto.SearchIn;
import com.mcflythekid.lazylearncore.outdto.BootstraptableOut;
import com.mcflythekid.lazylearncore.repo.MinpairRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class MinpairService {

    @Autowired
    private FileService fileService;
    @Autowired
    private MinpairRepo minpairRepo;

    @Transactional(rollbackFor = Exception.class)
    public Minpair create(MinpairCreateIn in, String userId) throws Exception{
        Minpair minpair = new Minpair();
        minpair.setUserId(userId);
        BeanUtils.copyProperties(in, minpair);
        minpairRepo.save(minpair);

        minpair.generateAudioPaths();
        minpairRepo.save(minpair);

        fileService.upload(minpair.getAudioPath1(), in.getAudio1());
        fileService.upload(minpair.getAudioPath2(), in.getAudio2());
        return minpair;
    }

    @Transactional(rollbackFor = Exception.class)
    public void learned(String minpairId){
        Minpair minpair = minpairRepo.findOne(minpairId);
        minpair.increaseLearnedCount();
        minpairRepo.save(minpair);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String minpairId) throws Exception{
        Minpair minpair = minpairRepo.findOne(minpairId);
        fileService.delete(minpair.getAudioPath1());
        fileService.delete(minpair.getAudioPath2());
        minpairRepo.delete(minpairId);
    }

    public BootstraptableOut search(SearchIn in, String userId){
        List<Minpair> rows = minpairRepo.findAllByUserIdAndSearch(userId, in.getSearch(), in.getPageable());
        Long total = minpairRepo.countByUserIdAndSearch(userId, in.getSearch());
        return new BootstraptableOut(rows, total);
    }
}
