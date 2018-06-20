package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.entity.Vocabdeck;
import com.mcflythekid.lazylearncore.indto.SearchIn;
import com.mcflythekid.lazylearncore.indto.vocabdeck.VocabdeckCreateIn;
import com.mcflythekid.lazylearncore.indto.vocabdeck.VocabdeckRenameIn;
import com.mcflythekid.lazylearncore.outdto.BootstraptableOut;
import com.mcflythekid.lazylearncore.repo.VocabdeckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class VocabdeckService {

    @Autowired
    private VocabdeckRepo vocabdeckRepo;

    @Transactional(rollbackFor = Exception.class)
    public Vocabdeck create(VocabdeckCreateIn in, String userId) throws IOException {
        Vocabdeck vocabdeck = new Vocabdeck();
        vocabdeck.setName(in.getName());
        vocabdeck.setUserId(userId);
        vocabdeckRepo.save(vocabdeck);

        // TODO imple

        return vocabdeck;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String vocabdeckId) {
        vocabdeckRepo.delete(vocabdeckId);
        // TODo imple
    }

    @Transactional(rollbackFor = Exception.class)
    public void archive(String vocabdeckId) {
        Vocabdeck vocabdeck = vocabdeckRepo.findOne(vocabdeckId);
        vocabdeck.setArchived(Consts.CARDDECK_ARCHIVED);
        vocabdeckRepo.save(vocabdeck);
        // TODo imple
    }

    @Transactional(rollbackFor = Exception.class)
    public void unarchive(String vocabdeckId) {
        Vocabdeck vocabdeck = vocabdeckRepo.findOne(vocabdeckId);
        vocabdeck.setArchived(Consts.CARDDECK_UNARCHIVED);
        vocabdeckRepo.save(vocabdeck);
        // TODo imple
    }

    @Transactional(rollbackFor = Exception.class)
    public void rename(VocabdeckRenameIn in) {
        Vocabdeck vocabdeck = vocabdeckRepo.findOne(in.getVocabdeckId());
        vocabdeck.setName(in.getNewName());
        vocabdeckRepo.save(vocabdeck);
    }

    public BootstraptableOut search(SearchIn in, String userId) {
        List<Vocabdeck> rows = vocabdeckRepo.findAllByUserIdAndNameContainingIgnoreCase(userId, in.getSearch(), in.getPageable());
        Long total = vocabdeckRepo.countByUserIdAndNameContainingIgnoreCase(userId, in.getSearch());
        return new BootstraptableOut(rows, total);
    }
}