package com.lazylearn.api.service;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.DetailedDeck;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.indto.deck.DeckCreateIn;
import com.lazylearn.api.indto.deck.DeckRenameIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.DeckRepo;
import com.lazylearn.api.repo.DetailedDeckRepo;
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
    private DetailedDeckRepo detailedDeckRepo;
    @Autowired
    private CardRepo cardRepo;

    @Transactional(rollbackFor = Exception.class)
    public void updateCallback(Deck deck){
        cardRepo.setArchivedAllByDeckId(deck.getArchived(), deck.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCallback(String deckId){
        cardRepo.deleteAllByDeckId(deckId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Deck deck){
        deckRepo.save(deck);
        updateCallback(deck);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(Deck deck){
        deckRepo.save(deck);
    }

    @Transactional(rollbackFor = Exception.class)
    public void rename(DeckRenameIn in) {
        Deck deck = deckRepo.findOne(in.getDeckId());
        deck.setName(in.getNewName());
        deckRepo.save(deck);
        //updateCallback(deck);
    }

    @Transactional(rollbackFor = Exception.class)
    public void archive(String deckId) {
        Deck deck = deckRepo.findOne(deckId);
        deck.setArchived(Consts.CARDDECK_ARCHIVED);
        deckRepo.save(deck);
        updateCallback(deck);
    }

    @Transactional(rollbackFor = Exception.class)
    public void unarchive(String deckId) {
        Deck deck = deckRepo.findOne(deckId);
        deck.setArchived(Consts.CARDDECK_UNARCHIVED);
        deckRepo.save(deck);
        updateCallback(deck);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String deckId) {
        deckRepo.delete(deckId);
        deleteCallback(deckId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Deck create(DeckCreateIn in, String userId){
        Deck deck = new Deck();
        deck.setName(in.getName());
        deck.setUserId(userId);
        return deckRepo.save(deck);
    }

    public Deck get(String deckId) {
        return deckRepo.findOne(deckId);
    }

    public BootstraptableOut search(SearchIn in, String userId){
        List<DetailedDeck> rows = detailedDeckRepo.findAllByUserIdAndNameContainingIgnoreCase(userId, in.getSearch(), in.getPageable());
        Long total = detailedDeckRepo.countByUserIdAndNameContainingIgnoreCase(userId, in.getSearch());
        return new BootstraptableOut(rows, total);
    }
}