package com.lazylearn.api.service;

import com.lazylearn.api.entity.Card;
import com.lazylearn.api.indto.card.CardCreateIn;
import com.lazylearn.api.indto.card.CardEditIn;
import com.lazylearn.api.indto.card.CardSearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.repo.CardRepo;
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