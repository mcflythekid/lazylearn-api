package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.DetailedDeck;
import com.mcflythekid.lazylearncore.indto.SearchIn;
import com.mcflythekid.lazylearncore.indto.deck.DeckCreateIn;
import com.mcflythekid.lazylearncore.indto.deck.DeckEditIn;
import com.mcflythekid.lazylearncore.outdto.BootstraptableOut;
import com.mcflythekid.lazylearncore.repo.CardRepo;
import com.mcflythekid.lazylearncore.repo.DeckRepo;
import com.mcflythekid.lazylearncore.repo.DetailedDeckRepo;
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
    public void edit(DeckEditIn in) {
        Deck deck = deckRepo.findOne(in.getDeckId());
        deck.setName(in.getNewName());
        deckRepo.save(deck);
    }

    @Transactional(rollbackFor = Exception.class)
    public void archive(String deckId) {
        Deck deck = deckRepo.findOne(deckId);
        deck.setArchived(Consts.CARDDECK_ARCHIVED);
        deckRepo.save(deck);
        cardRepo.archiveAllByDeckId(deckId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void unarchive(String deckId) {
        Deck deck = deckRepo.findOne(deckId);
        deck.setArchived(Consts.CARDDECK_UNARCHIVED);
        deckRepo.save(deck);
        cardRepo.unarchiveAllByDeckId(deckId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String deckId) {
        deckRepo.delete(deckId);
        cardRepo.deleteAllByDeckId(deckId);
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