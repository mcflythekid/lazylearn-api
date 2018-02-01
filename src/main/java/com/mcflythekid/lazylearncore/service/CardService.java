package com.mcflythekid.lazylearncore.service;
import java.util.Date;

import com.mcflythekid.lazylearncore.Const;
import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.indto.CreateCardInDto;
import com.mcflythekid.lazylearncore.indto.SearchCardInDto;
import com.mcflythekid.lazylearncore.indto.UpdateCardInDto;
import com.mcflythekid.lazylearncore.outdto.BootstrapTableOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
@Transactional
public class CardService {

    @Autowired
    private AuthService authService;

    @Autowired
    private CardRepo cardRepo;

    public BootstrapTableOutDto search(SearchCardInDto searchCardInDto) {
        List<Card> rows = cardRepo.findAllByDeckIdAndSearch(searchCardInDto.getDeckId(),  searchCardInDto.getSearch(),
                searchCardInDto.getPageable());
        Long total = cardRepo.countByDeckIdAndSearch(searchCardInDto.getDeckId(), searchCardInDto.getSearch());
        return new BootstrapTableOutDto(rows, total);
    }

    public JSON create(CreateCardInDto createCardInDto) {
        Card card = new Card();
        card.setId(authService.getRamdomId());
        card.setFront(createCardInDto.getFront());
        card.setBack(createCardInDto.getBack());
        card.setCreatedOn(new Date());
        card.setWakeupOn(new Date());
        card.setDeckId(createCardInDto.getDeckId());
        card.setUserId(createCardInDto.getUserId());
        card.setStep(Const.CARD_STEP_FRESH);
        cardRepo.save(card);
        return JSON.ok();
    }

    public JSON delete(String cardId) {
        cardRepo.delete(cardId);
        return JSON.ok();
    }

    public JSON update(UpdateCardInDto updateCardInDto) {
        Card card = cardRepo.findOne(updateCardInDto.getCardId());
        card.setFront(updateCardInDto.getFront());
        card.setBack(updateCardInDto.getBack());
        card.setUpdatedOn(new Date());
        cardRepo.save(card);
        return JSON.ok();
    }
}
