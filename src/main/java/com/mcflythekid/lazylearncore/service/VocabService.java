package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.Vocab;
import com.mcflythekid.lazylearncore.indto.SearchIn;
import com.mcflythekid.lazylearncore.indto.vocab.VocabCreateIn;
import com.mcflythekid.lazylearncore.indto.vocab.VocabEditIn;
import com.mcflythekid.lazylearncore.outdto.BootstraptableOut;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.repo.VocabRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author McFly the Kid
 */
@Service
public class VocabService {

    @Autowired
    private VocabRepo vocabRepo;

    @Transactional(rollbackFor = Exception.class)
    public Vocab create(VocabCreateIn in){
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void edit(VocabEditIn in){
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String vocabId){
    }

    public BootstraptableOut search(SearchIn in, String userId){
        return null;
    }
}
