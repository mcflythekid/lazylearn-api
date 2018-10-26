package com.lazylearn.api.service;

import com.lazylearn.api.config.Consts;
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

    public List<ChartRow> deckChart(String deckId){
        List<ChartRow> out = new ArrayList<>();
        for (int step = Consts.CARD_STEP_BEGIN; step <= Consts.CARD_STEP_END; step++){
            out.add(new ChartRow(
                Consts.CARD_STEP_LABEL_MAP.get(step),
                countCorrectByDeckAndStep(deckId, step),
                countTimeupByDeckAndStep(deckId, step)
            ));
        }
        return out;
    }

    public List<ChartRow> userChart(String userId){
        List<ChartRow> out = new ArrayList<>();
        for (int step = Consts.CARD_STEP_BEGIN; step <= Consts.CARD_STEP_END; step++){
            out.add(new ChartRow(
                    Consts.CARD_STEP_LABEL_MAP.get(step),
                    countCorrectByUserAndStep(userId, step),
                    countTimeupByUserAndStep(userId, step)
            ));
        }
        return out;
    }

    private Long countCorrectByUserAndStep(String userId, Integer step){
        if (step == Consts.CARD_STEP_END) return cardRepo.countAllByUserIdAndStepAndArchived(userId, step, Consts.CARDDECK_UNARCHIVED);
        return cardRepo.countAllByUserIdAndStepAndWakeupOnAfterAndArchived(userId, step, new Date(), Consts.CARDDECK_UNARCHIVED);
    }
    private Long countTimeupByUserAndStep(String userId, Integer step){
        if (step == Consts.CARD_STEP_END) return 0L;
        return cardRepo.countAllByUserIdAndStepAndWakeupOnBeforeAndArchived(userId, step, new Date(), Consts.CARDDECK_UNARCHIVED);
    }
    private Long countCorrectByDeckAndStep(String deckId, Integer step){
        if (step == Consts.CARD_STEP_END) return cardRepo.countAllByDeckIdAndStepAndArchived(deckId, step, Consts.CARDDECK_UNARCHIVED);
        return cardRepo.countAllByDeckIdAndStepAndWakeupOnAfterAndArchived(deckId, step, new Date(), Consts.CARDDECK_UNARCHIVED);
    }
    private Long countTimeupByDeckAndStep(String deckId, Integer step){
        if (step == Consts.CARD_STEP_END) return 0L;
        return cardRepo.countAllByDeckIdAndStepAndWakeupOnBeforeAndArchived(deckId, step, new Date(), Consts.CARDDECK_UNARCHIVED);
    }
}
