package com.lazylearn.api.service;

import com.lazylearn.api.entity.MinpairFile;
import com.lazylearn.api.indto.minpair.MinpairFileCreateIn;
import com.lazylearn.api.repo.MinpairFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 4nha
 * Date: 2020-04-26
 */
@Service
public class MinpairFileService {

    @Autowired
    private MinpairFileRepo minpairFileRepo;

    @Autowired
    private FileService fileService;

    public MinpairFile upload(MinpairFileCreateIn dto){
        return null;
    }

    public MinpairFile rename(MinpairFile minpairFile, String newName){
        minpairFile.setName(newName);
        return minpairFileRepo.save(minpairFile);
    }

    @Transactional
    public void delete(MinpairFile minpairFile) throws Exception {
        fileService.delete(minpairFile.getAudioPath());
        minpairFileRepo.delete(minpairFile.getId());
    }

}
