package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.indto.card.CardCreateIn;
import com.mcflythekid.lazylearncore.indto.card.CardEditIn;
import com.mcflythekid.lazylearncore.indto.card.CardSearchIn;
import com.mcflythekid.lazylearncore.outdto.BootstraptableOut;
import com.mcflythekid.lazylearncore.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class CardService {

    @Autowired
    private CardRepo cardRepo;

    public BootstraptableOut search(CardSearchIn in) {
        List<Card> rows = cardRepo.findAllByDeckIdAndSearch(in.getDeckId(),  in.getSearch(), in.getPageable());
        Long total = cardRepo.countByDeckIdAndSearch(in.getDeckId(), in.getSearch());
        return new BootstraptableOut(rows, total);
    }

    @Transactional(rollbackFor = Exception.class)
    public Card create(CardCreateIn in, String userId) {
        Card card = new Card();
        card.setFront(in.getFront());
        card.setBack(in.getBack());
        card.setDeckId(in.getDeckId());
        card.setUserId(userId);
        return cardRepo.save(card);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String cardId) {
        cardRepo.delete(cardId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Card edit(CardEditIn in) {
        Card card = cardRepo.findOne(in.getCardId());
        card.setFront(in.getFront());
        card.setBack(in.getBack());
        return cardRepo.save(card);
    }
}