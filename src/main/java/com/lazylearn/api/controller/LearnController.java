package com.lazylearn.api.controller;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.outdto.LearnOut;
import com.lazylearn.api.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/learn")
public class LearnController extends BaseController{

    @Autowired
    private LearnService learnService;

    @GetMapping("/get-deck")
    public LearnOut get(@RequestParam("deckId") String deckId, @RequestParam("learnType") String learnType) throws Exception {
        Deck deck = authorizeDeck(deckId);

        if (learnType.equals(Consts.LEARNTYPE_LEARN)){
            return learnService.getByLearn(deck);
        } else if (learnType.equals(Consts.LEARNTYPE_REVIEW)){
            return learnService.getByReview(deck);
        }

        throw new AppException(404, "Learn type not found: " + learnType);
    }

    @PostMapping("/correct/{cardId}")
    public JSON correct(@PathVariable("cardId") String cardId) throws Exception {
        Card card = authorizeCard(cardId);
        learnService.markCorrect(card);
        return JSON.ok();
    }

    @PostMapping("/incorrect/{cardId}")
    public JSON incorrect(@PathVariable("cardId") String cardId) throws Exception {
        Card card = authorizeCard(cardId);
        learnService.markIncorrect(card);
        return JSON.ok();
    }
}
