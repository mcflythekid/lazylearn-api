package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.Const;
import com.mcflythekid.lazylearncore.outdto.ChartRow;
import com.mcflythekid.lazylearncore.repo.CardRepo;
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
        for (int step = Const.CARD_STEP_BEGIN; step <= Const.CARD_STEP_END; step++){
            out.add(new ChartRow(
                Const.CARD_STEP_LABEL_MAP.get(step),
                countCorrectByDeckAndStep(deckId, step),
                countTimeupByDeckAndStep(deckId, step)
            ));
        }
        return out;
    }

    public List<ChartRow> userChart(String userId){
        List<ChartRow> out = new ArrayList<>();
        for (int step = Const.CARD_STEP_BEGIN; step <= Const.CARD_STEP_END; step++){
            out.add(new ChartRow(
                    Const.CARD_STEP_LABEL_MAP.get(step),
                    countCorrectByUserAndStep(userId, step),
                    countTimeupByUserAndStep(userId, step)
            ));
        }
        return out;
    }

    private Long countCorrectByUserAndStep(String userId, Integer step){
        if (step == Const.CARD_STEP_END) return cardRepo.countAllByUserIdAndStepAndArchived(userId, step, Const.CARDDECK_UNARCHIVED);
        return cardRepo.countAllByUserIdAndStepAndWakeupOnAfterAndArchived(userId, step, new Date(), Const.CARDDECK_UNARCHIVED);
    }
    private Long countTimeupByUserAndStep(String userId, Integer step){
        if (step == Const.CARD_STEP_END) return 0L;
        return cardRepo.countAllByUserIdAndStepAndWakeupOnBeforeAndArchived(userId, step, new Date(), Const.CARDDECK_UNARCHIVED);
    }
    private Long countCorrectByDeckAndStep(String deckId, Integer step){
        if (step == Const.CARD_STEP_END) return cardRepo.countAllByDeckIdAndStepAndArchived(deckId, step, Const.CARDDECK_UNARCHIVED);
        return cardRepo.countAllByDeckIdAndStepAndWakeupOnAfterAndArchived(deckId, step, new Date(), Const.CARDDECK_UNARCHIVED);
    }
    private Long countTimeupByDeckAndStep(String deckId, Integer step){
        if (step == Const.CARD_STEP_END) return 0L;
        return cardRepo.countAllByDeckIdAndStepAndWakeupOnBeforeAndArchived(deckId, step, new Date(), Const.CARDDECK_UNARCHIVED);
    }
}
