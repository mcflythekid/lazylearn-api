package com.lazylearn.api.learnprogram;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.repo.CardRepo;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service(Consts.STEP_PROGRAM__SM2)
public class Sm2Program implements Program {

    @Autowired
    private CardRepo cardRepo;

    private Double calculateAndControlEFactor(Double oldEFactor, Integer quality){
        if (!ArrayUtils.contains(Consts.SM2_ALLOWS_CORRECT_QUALITY, quality)){
            throw new AppException(401, "Not allowed setQuality value: " + quality);
        }
        Double newEFaqctor = oldEFactor - 0.8 + 0.28*quality - 0.02*quality*quality;
        if (newEFaqctor < Consts.SM2_EF_MIN){
            newEFaqctor = Consts.SM2_EF_MIN;
        }
        return newEFaqctor;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setCorrect(Card card) {
        setQuality(card, Consts.SM2_CORRECT_QUALITY_DEFAULT);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setQuality(Card card, Integer quality) {

        Integer newStep = card.getStep() + 1;
        Double newEFactor = calculateAndControlEFactor(card.getSm2Ef(), quality);
        Integer newSpace = newStep <= 2 ? Consts.SM2_SPACE_STEP[newStep] : Long.valueOf(Math.round(newEFactor * card.getSm2LatestSpace())).intValue();

        card.setSm2Ef(newEFactor);
        card.setSm2LatestSpace(newSpace);
        card.setLearnedOn(new Date());
        card.setWakeupDays(newSpace);
        card.increaseStep();
        cardRepo.save(card);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setIncorrect(Card card) {
        Date now = new Date();
        card.setSm2Ef(calculateAndControlEFactor(card.getSm2Ef(), Consts.SM2_INCORRECT_QUALITY));
        card.setSm2LatestSpace(0);
        card.setLearnedOn(now);
        card.setWakeupOn(now);
        card.increaseStep();
        cardRepo.save(card);
    }
}
