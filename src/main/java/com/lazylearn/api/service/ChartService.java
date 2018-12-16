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

    public List<ChartRow> deckChart(Deck deck){
        List<ChartRow> out = new ArrayList<>();
        for (int step = 0; step <= programService.getCardStepEnd(deck.getProgramId()); step++){
            out.add(new ChartRow(
                programService.getLabel(deck.getProgramId(), step),
                countCorrectByDeckAndStep(deck.getId(), step, deck.getProgramId()),
                countTimeupByDeckAndStep(deck.getId(), step, deck.getProgramId())
            ));
        }
        return out;
    }

    public List<ChartRow> userChart(String userId){
        List<ChartRow> out = new ArrayList<>();
        for (int step = 0; step <= programService.getCardStepEndForUser(); step++){
            out.add(new ChartRow(
                    programService.getLabelForUser(step),
                    countCorrectByUserAndStep(userId, step, Consts.STEP_PROGRAM_BIGGEST),
                    countTimeupByUserAndStep(userId, step, Consts.STEP_PROGRAM_BIGGEST)
            ));
        }
        return out;
    }

    private Long countCorrectByUserAndStep(String userId, Integer step, String programId){
        if (step == programService.getCardStepEnd(programId)) return cardRepo.countAllByUserIdAndStepAndArchived(userId, step, Consts.CARDDECK_UNARCHIVED);
        return cardRepo.countAllByUserIdAndStepAndWakeupOnAfterAndArchived(userId, step, new Date(), Consts.CARDDECK_UNARCHIVED);
    }
    private Long countTimeupByUserAndStep(String userId, Integer step, String programId){
        if (step == programService.getCardStepEnd(programId)) return 0L;
        return cardRepo.countAllByUserIdAndStepAndWakeupOnBeforeAndArchived(userId, step, new Date(), Consts.CARDDECK_UNARCHIVED);
    }
    private Long countCorrectByDeckAndStep(String deckId, Integer step, String programId){
        if (step == programService.getCardStepEnd(programId)) return cardRepo.countAllByDeckIdAndStepAndArchived(deckId, step, Consts.CARDDECK_UNARCHIVED);
        return cardRepo.countAllByDeckIdAndStepAndWakeupOnAfterAndArchived(deckId, step, new Date(), Consts.CARDDECK_UNARCHIVED);
    }
    private Long countTimeupByDeckAndStep(String deckId, Integer step, String programId){
        if (step == programService.getCardStepEnd(programId)) return 0L;
        return cardRepo.countAllByDeckIdAndStepAndWakeupOnBeforeAndArchived(deckId, step, new Date(), Consts.CARDDECK_UNARCHIVED);
    }
}
