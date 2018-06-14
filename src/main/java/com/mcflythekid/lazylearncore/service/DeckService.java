package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.DetailedDeck;
import com.mcflythekid.lazylearncore.indto.CreateDeckInDto;
import com.mcflythekid.lazylearncore.indto.UpdateDeckInDto;
import com.mcflythekid.lazylearncore.indto.SearchDeckInDto;
import com.mcflythekid.lazylearncore.outdto.BootstrapTableOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.repo.CardRepo;
import com.mcflythekid.lazylearncore.repo.DeckRepo;
import com.mcflythekid.lazylearncore.repo.VDeckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class DeckService {

    @Autowired
    private DeckRepo deckRepo;
    @Autowired
    private VDeckRepo vDeckRepo;
    @Autowired
    private CardRepo cardRepo;

    @Transactional(rollbackFor = Exception.class)
    public JSON create(CreateDeckInDto createDeckInDto){
        Deck deck = new Deck();
        deck.setName(createDeckInDto.getName());
        deck.setUserId(createDeckInDto.getUserId());
        deck.setArchived(Consts.CARDDECK_UNARCHIVED);
        deckRepo.save(deck);
        return JSON.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public JSON update(UpdateDeckInDto updateDeckInDto) {
        Deck deck = deckRepo.findOne(updateDeckInDto.getDeckId());
        deck.setName(updateDeckInDto.getName());
        deckRepo.save(deck);
        return JSON.ok();
    }

    public Deck get(String deckId) {
        return deckRepo.findOne(deckId);
    }

    @Transactional(rollbackFor = Exception.class)
    public JSON delete(String deckId) {
        deckRepo.delete(deckId);
        cardRepo.deleteAllByDeckId(deckId);
        return JSON.ok();
    }

    public BootstrapTableOutDto search(SearchDeckInDto searchDeckInDto){
        List<DetailedDeck> rows = vDeckRepo.findAllByUserIdAndNameContainingIgnoreCase(searchDeckInDto.getUserId(),
                searchDeckInDto.getSearch(), searchDeckInDto.getPageable());
        Long total = vDeckRepo.countByUserIdAndNameContainingIgnoreCase(searchDeckInDto.getUserId(), searchDeckInDto.getSearch());
        return new BootstrapTableOutDto(rows, total);
    }

    @Transactional(rollbackFor = Exception.class)
    public JSON archive(String deckId) {
        Deck deck = deckRepo.findOne(deckId);
        deck.setArchived(Consts.CARDDECK_ARCHIVED);
        deckRepo.save(deck);
        cardRepo.archiveAllByDeckId(deckId);
        return JSON.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public JSON unarchive(String deckId) {
        Deck deck = deckRepo.findOne(deckId);
        deck.setArchived(Consts.CARDDECK_UNARCHIVED);
        deckRepo.save(deck);
        cardRepo.unarchiveAllByDeckId(deckId);
        return JSON.ok();
    }
}