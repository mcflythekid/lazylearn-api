package com.lazylearn.api.controller;

import com.google.common.collect.ImmutableMap;
import com.lazylearn.api.config.Consts;
import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.User;
import com.lazylearn.api.indto.learn.QualityIn;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.outdto.LearnOut;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.UserRepo;
import com.lazylearn.api.service.AnswerProcessService;
import com.lazylearn.api.service.LearnService;
import com.lazylearn.api.unit.TelegramUnit;
import com.lazylearn.api.util.DateUtils2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/learn")
public class LearnController extends BaseController {

    @Autowired
    private TelegramUnit telegramUnit;

    @Autowired
    private LearnService learnService;

    @Autowired
    private AnswerProcessService answerProcessService;

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private UserRepo userRepo;

    private String getUserFullName() throws Exception {
        User user = userRepo.findOne(getUserId());
        return user.getFullName();
    }

    @GetMapping("/count-onetime-learn-card")
    public Map countOnetimeLearnCard() throws Exception {
        return ImmutableMap.of("count", cardRepo.countAllByUserIdAndWakeupOnBefore(getUserId(), new Date()));
    }

    @GetMapping("/count-today-onetime-learn-card")
    public Map countTodayOnetimeLearnCard() throws Exception {
        User user = userRepo.findOne(getUserId());
        return ImmutableMap.of("count", cardRepo.countAllByUserIdAndWakeupOnBeforeAndLearnedOnBefore(getUserId(), new Date(), DateUtils2.getRemoteTimezoneMidnightDate(user.getTimezone())));
    }

    @GetMapping("/get-deck")
    public LearnOut get(@RequestParam("deckId") String deckId, @RequestParam("learnType") String learnType) throws Exception {

        // All deck
        if (Consts.Deck.LEARN_ALL_DECK_ID.equals(deckId)) {
            telegramUnit.sendAsync("Learn one-for-all by " + getUserFullName(), getUserFullName());
            return learnService.getByLearnOneUserId(this.getUserId());
        }

        // All deck today
        if (Consts.Deck.LEARN_ALL_DECK_TODAY_ID.equals(deckId)) {
            telegramUnit.sendAsync("Learn one-for-all-today by " + getUserFullName(), getUserFullName());
            return learnService.getByLearnOneUserIdOnetimeToday(this.getUserId());
        }

        // Normal
        Deck deck = authorizeDeck(deckId);
        telegramUnit.sendAsync("Learn by " + getUserFullName(), getUserFullName());
        if (learnType.equals(Consts.LEARNTYPE_LEARN)) {
            return learnService.getByLearn(deck);
        } else if (learnType.equals(Consts.LEARNTYPE_REVIEW)) {
            return learnService.getByReview(deck);
        }

        throw new AppException(404, "Learn type not found: " + learnType);
    }

    @GetMapping("/learnable-count/{deckId}")
    public Integer checkLearnable(@PathVariable String deckId) throws Exception {
        Deck deck = authorizeDeck(deckId);
        LearnOut data = learnService.getByLearn(deck);
        return data.getCards().size();
    }

    @PostMapping("/incorrect/{cardId}")
    public JSON setIncorrect(@PathVariable("cardId") String cardId) throws Exception {
        Card card = authorizeCard(cardId);
        answerProcessService.setIncorrect(card);
        return JSON.ok();
    }

    @PostMapping("/quality")
    public JSON setQuality(@Valid @RequestBody QualityIn payload) throws Exception {
        Card card = authorizeCard(payload.getCardId());
        answerProcessService.setQuality(card, payload.getQuality());
        return JSON.ok();
    }
}
