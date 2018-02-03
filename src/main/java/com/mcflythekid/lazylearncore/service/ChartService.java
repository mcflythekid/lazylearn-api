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
        if (step == Const.CARD_STEP_END) return cardRepo.countAllByUserIdAndStep(userId, step);
        return cardRepo.countAllByUserIdAndStepAndWakeupOnAfter(userId, step, new Date());
    }
    private Long countTimeupByUserAndStep(String userId, Integer step){
        if (step == Const.CARD_STEP_END) return 0L;
        return cardRepo.countAllByUserIdAndStepAndWakeupOnBefore(userId, step, new Date());
    }
    private Long countCorrectByDeckAndStep(String deckId, Integer step){
        if (step == Const.CARD_STEP_END) return cardRepo.countAllByDeckIdAndStep(deckId, step);
        return cardRepo.countAllByDeckIdAndStepAndWakeupOnAfter(deckId, step, new Date());
    }
    private Long countTimeupByDeckAndStep(String deckId, Integer step){
        if (step == Const.CARD_STEP_END) return 0L;
        return cardRepo.countAllByDeckIdAndStepAndWakeupOnBefore(deckId, step, new Date());
    }
}
