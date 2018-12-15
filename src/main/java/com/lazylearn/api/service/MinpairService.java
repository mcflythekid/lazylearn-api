package com.lazylearn.api.service;

import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.Minpair;
import com.lazylearn.api.indto.minpair.MinpairCreateIn;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.DeckRepo;
import com.lazylearn.api.repo.MinpairRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.MediaSize;
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

    @Transactional(rollbackFor = Exception.class)
    public Deck sendToDeck(String minpairId, String userId) throws Exception{
        final String NAME_REFIX = "Minpair #";
        Minpair minpair = minpairRepo.findOne(minpairId);

        Deck deck = deckRepo.findByMinpairLanguageAndUserId(minpair.getLanguage(), userId);
        if (deck == null){
            deck = deckService.create(NAME_REFIX + minpair.getLanguage(), userId);
            deck.setMinpairLanguage(minpair.getLanguage());
            deckRepo.save(deck);
        }
        Card card = cardRepo.findByDeckIdAndFront(deck.getId(), minpairId);
        if(card == null){
            cardService.create(minpairId, "", deck.getId(), userId);
        } else {
            throw new AppException(401, "This minpair is already get");
        }

        return deck;
    }

    public BootstraptableOut search(SearchIn in, String userId){
        List<Minpair> rows = minpairRepo.findAllByUserIdAndSearch(userId, in.getSearch(), in.getPageable());
        Long total = minpairRepo.countByUserIdAndSearch(userId, in.getSearch());
        return new BootstraptableOut(rows, total);
    }
}
