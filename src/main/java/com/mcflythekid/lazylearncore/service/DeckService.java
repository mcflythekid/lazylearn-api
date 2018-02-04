package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.VDeck;
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

import java.util.Date;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
@Transactional
public class DeckService {

    @Autowired
    private DeckRepo deckRepo;

    @Autowired
    private VDeckRepo vDeckRepo;

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private AuthService authService;

    public JSON create(CreateDeckInDto createDeckInDto){
        Deck deck = new Deck();
        deck.setCreatedOn(new Date());
        deck.setId(authService.getRamdomId());
        deck.setName(createDeckInDto.getName());
        deck.setUserId(createDeckInDto.getUserId());
        deckRepo.save(deck);
        return JSON.ok();
    }

    public JSON update(UpdateDeckInDto updateDeckInDto) {
        Deck deck = deckRepo.findOne(updateDeckInDto.getDeckId());
        deck.setUpdatedOn(new Date());
        deck.setName(updateDeckInDto.getName());
        deckRepo.save(deck);
        return JSON.ok();
    }

    public Deck get(String deckId) {
        return deckRepo.findOne(deckId);
    }

    public JSON delete(String deckId) {
        deckRepo.delete(deckId);
        cardRepo.deleteAllByDeckId(deckId);
        return JSON.ok();
    }

    public BootstrapTableOutDto search(SearchDeckInDto searchDeckInDto){
        List<VDeck> rows = vDeckRepo.findAllByUserIdAndNameContainingIgnoreCase(searchDeckInDto.getUserId(),
                searchDeckInDto.getSearch(), searchDeckInDto.getPageable());
        Long total = vDeckRepo.countByUserIdAndNameContainingIgnoreCase(searchDeckInDto.getUserId(), searchDeckInDto.getSearch());
        return new BootstrapTableOutDto(rows, total);
    }
}
