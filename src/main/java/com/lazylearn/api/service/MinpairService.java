package com.lazylearn.api.service;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.Minpair;
import com.lazylearn.api.indto.minpair.MinpairCreateIn;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.DeckRepo;
import com.lazylearn.api.repo.MinpairFileRepo;
import com.lazylearn.api.repo.MinpairRepo;
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
    @Autowired
    private DeckRepo deckRepo;
    @Autowired
    private DeckService deckService;
    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private MinpairFileService minpairFileService;
    @Autowired
    private MinpairFileRepo minpairFileRepo;

    @Transactional(rollbackFor = Exception.class)
    public Minpair create(MinpairCreateIn in, String userId) throws Exception{
        Minpair minpair = new Minpair();
        minpair.setUserId(userId);
        BeanUtils.copyProperties(in, minpair);
        minpair = minpairRepo.save(minpair);

        minpairFileService.create(in, minpair);
        updateCardAndDeckCallBack(minpair.getId());
        return minpair;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String minpairId) throws Exception{
        Minpair minpair = minpairRepo.findOne(minpairId);
        minpairRepo.delete(minpairId);

        minpairFileRepo.deleteAllByMinpairId(minpairId);
        fileService.rmDirIfExists(minpair.getDirPath());
    }

    @Transactional(rollbackFor = Exception.class)
    public Deck updateCardAndDeckCallBack(String minpairId) throws Exception{
        final String NAME_REFIX = "All Minpair #";
        Minpair minpair = minpairRepo.findOne(minpairId);

        Deck deck = deckRepo.findByMinpairLanguageAndUserId(minpair.getLanguage(), minpair.getUserId());
        if (deck == null){
            deck = deckService.create(NAME_REFIX + minpair.getLanguage(), minpair.getUserId());
            deck.setMinpairLanguage(minpair.getLanguage());
        }
        deck.setType(Consts.DECKTYPE__MINPAIR);
        deckRepo.save(deck);
        Card card = cardRepo.findByDeckIdAndFront(deck.getId(), minpairId);
        if(card == null){
            card = cardService.create(minpairId, "", deck.getId(), minpair.getUserId());
        }
        card.setMinpairLanguage(minpair.getLanguage());
        card.setFront(minpairId);
        cardRepo.save(card);

        return deck;
    }

    public BootstraptableOut searchByKeywordAndUserId(SearchIn in, String userId){
        List<Minpair> rows = minpairRepo.findAllByKeywordAndUserId( in.getSearch(), userId, in.getPageable());
        Long total = minpairRepo.countByKeywordAndUserId(in.getSearch(), userId);
        return new BootstraptableOut(rows, total);
    }

    public BootstraptableOut searchByKeyword(SearchIn in){
        List<Minpair> rows = minpairRepo.findAllByKeyword(in.getSearch(), in.getPageable());
        Long total = minpairRepo.countByKeyword(in.getSearch());
        return new BootstraptableOut(rows, total);
    }
}
