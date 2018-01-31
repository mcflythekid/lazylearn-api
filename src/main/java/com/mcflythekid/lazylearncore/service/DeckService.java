package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.indto.CreateDeckInDto;
import com.mcflythekid.lazylearncore.indto.UpdateDeckInDto;
import com.mcflythekid.lazylearncore.indto.SearchDeckInDto;
import com.mcflythekid.lazylearncore.outdto.BootstrapTableOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.repo.DeckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class DeckService {

    @Autowired
    private DeckRepo deckRepo;

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
        return JSON.ok();
    }

    public BootstrapTableOutDto search(SearchDeckInDto searchDeckInDto){
        List<Deck> rows = deckRepo.findAllByUserIdAndNameContaining(searchDeckInDto.getUserId(),
                searchDeckInDto.getSearch(), searchDeckInDto.getPageable());
        Long total = deckRepo.countByUserIdAndNameContaining(searchDeckInDto.getUserId(), searchDeckInDto.getSearch());
        return new BootstrapTableOutDto(rows, total);
    }
}
