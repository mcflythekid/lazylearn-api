package com.lazylearn.api.program;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.repo.CardRepo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service(Consts.PROGRAM__SM2)
public class Sm2Program implements Program {

    public static final Double SM2_EF_MIN = 1.3D;
    public static final Double SM2_EF_INIT = 2.5D;
    public static final Integer[] SM2_SPACE_STEP = new Integer[]{0, 1, 6};
    public static final Integer[] SM2_ALLOWS_CORRECT_QUALITY = new Integer[]{3, 4, 5};
    public static final Integer SM2_INCORRECT_QUALITY = 0;
    public static final Integer SM2_CORRECT_QUALITY_DEFAULT = 5;

    @Autowired
    private CardRepo cardRepo;

    private Double calculateAndControlEFactor(Double oldEFactor, Integer quality) {
        if (!ArrayUtils.contains(SM2_ALLOWS_CORRECT_QUALITY, quality)) {
            throw new AppException(401, "Not allowed setQuality value: " + quality);
        }
        Double newEFaqctor = oldEFactor - 0.8 + 0.28 * quality - 0.02 * quality * quality;
        if (newEFaqctor < SM2_EF_MIN) {
            newEFaqctor = SM2_EF_MIN;
        }
        return newEFaqctor;
    }

    /**
     * CORRECT
     *
     * @param card
     * @param quality
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setQuality(Card card, Integer quality) {

        if (!card.isExpired()) {
            throw new AppException(401, "Not expired yet");
        }

        final Double newEFactor = calculateAndControlEFactor(card.getSm2Ef(), quality);

        if (quality == 3) { // Min is 3, max is 5. No 0, 1, 2.
            card.setSm2Ef(newEFactor);
            card.setWakeupOn(new Date());
            card.setLearnedOn(new Date());
        } else {
            final Integer newStep = card.getStep() + 1;
            final Integer newSpace = newStep <= 2 ? SM2_SPACE_STEP[newStep] : Long.valueOf(Math.round(newEFactor * card.getSm2LatestSpace())).intValue();
            card.setSm2Ef(newEFactor);
            card.setSm2LatestSpace(newSpace);
            card.setLearnedOn(new Date());
            card.setWakeupOn(DateUtils.addDays(new Date(), newSpace));
            card.setStep(newStep);
        }

        cardRepo.save(card);
    }

    /**
     * INCORRECT
     * <p>
     * If the quality response was lower than 3 then start repetitions for the item from the beginning
     * without changing the E-Factor (i.e. use intervals I(1), I(2) etc. as if the item was memorized anew).
     *
     * @param card
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setIncorrect(Card card) {
        Date now = new Date();
        // no change sm2ef
        card.setSm2LatestSpace(0);
        card.setLearnedOn(now);
        card.setWakeupOn(now);
        card.setStep(0);
        cardRepo.save(card);
    }
}
