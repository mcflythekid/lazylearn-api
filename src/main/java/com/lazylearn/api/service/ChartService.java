package com.lazylearn.api.service;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.outdto.ChartRow;
import com.lazylearn.api.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class ChartService {

    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private ProgramService programService;

    public List<ChartRow> deckChart(Deck deck) {
        List<ChartRow> out = new ArrayList<>();
        for (int step = 0; step <= programService.getMaxStepOfDeck(deck.getId()); step++) {
            out.add(new ChartRow(
                    programService.getLabel(step),
                    countCorrectByDeckAndStep(deck.getId(), step),
                    countTimeupByDeckAndStep(deck.getId(), step)
            ));
        }
        return out;
    }

    public List<ChartRow> userChart(String userId) {
        List<ChartRow> out = new ArrayList<>();
        for (int step = 0; step <= programService.getMaxStepOfUser(userId); step++) {
            out.add(new ChartRow(
                    programService.getLabel(step),
                    countCorrectByUserAndStep(userId, step),
                    countTimeupByUserAndStep(userId, step)
            ));
        }
        return out;
    }

    private Long countCorrectByUserAndStep(String userId, Integer step) {
        return cardRepo.countAllByUserIdAndStepAndWakeupOnAfterAndArchived(userId, step, new Date(), Consts.CARDDECK_UNARCHIVED);
    }

    private Long countCorrectByDeckAndStep(String deckId, Integer step) {
        return cardRepo.countAllByDeckIdAndStepAndWakeupOnAfterAndArchived(deckId, step, new Date(), Consts.CARDDECK_UNARCHIVED);
    }

    private Long countTimeupByUserAndStep(String userId, Integer step) {
        return cardRepo.countAllByUserIdAndStepAndWakeupOnBeforeAndArchived(userId, step, new Date(), Consts.CARDDECK_UNARCHIVED);
    }

    private Long countTimeupByDeckAndStep(String deckId, Integer step) {
        return cardRepo.countAllByDeckIdAndStepAndWakeupOnBeforeAndArchived(deckId, step, new Date(), Consts.CARDDECK_UNARCHIVED);
    }
}
