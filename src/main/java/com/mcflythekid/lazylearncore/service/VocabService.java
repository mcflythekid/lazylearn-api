package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.Vocab;
import com.mcflythekid.lazylearncore.indto.SearchIn;
import com.mcflythekid.lazylearncore.indto.vocab.VocabCreateIn;
import com.mcflythekid.lazylearncore.indto.vocab.VocabEditIn;
import com.mcflythekid.lazylearncore.outdto.BootstraptableOut;
import com.mcflythekid.lazylearncore.repo.DeckRepo;
import com.mcflythekid.lazylearncore.repo.VocabRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class VocabService {

    @Autowired
    private VocabRepo vocabRepo;
    @Autowired
    private DeckService deckService;
    @Autowired
    private DeckRepo deckRepo;

    @Transactional(rollbackFor = Exception.class)
    public Vocab create(VocabCreateIn in){
        return null;
        // TODO
    }

    @Transactional(rollbackFor = Exception.class)
    public void edit(VocabEditIn in){
        // TODO
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String vocabId){
        vocabRepo.delete(vocabId);
        deckRepo.findAllByVocabId(vocabId).stream().forEach(deck -> {
            deckService.delete(deck.getId());
        });
    }

    public BootstraptableOut search(SearchIn in, String userId){
        List<Vocab> rows = vocabRepo.findAllByUserIdAndSearch(userId, in.getSearch(), in.getPageable());
        Long total = vocabRepo.countByUserIdAndSearch(userId, in.getSearch());
        return new BootstraptableOut(rows, total);
    }
}
