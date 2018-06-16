package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.config.exception.AppException;
import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.outdto.LearnOut;
import com.mcflythekid.lazylearncore.service.LearnService;
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
    public LearnOut get(@RequestParam("deckId") String deckId, @RequestParam("type") String type) throws Exception {
        Deck deck = authorizeDeck(deckId);

        if (type.equals(Consts.LEARNTYPE_LEARN)){
            return learnService.getByLearn(deck);
        } else if (type.equals(Consts.LEARNTYPE_REVIEW)){
            return learnService.getByReview(deck);
        }

        throw new AppException(404, "Learn type not found: " + type);
    }

    @PatchMapping("/correct/{cardId}")
    public JSON correct(@PathVariable("cardId") String cardId) throws Exception {
        Card card = authorizeCard(cardId);
        learnService.markCorrect(card);
        return JSON.ok();
    }

    @PatchMapping("/card/{cardId}/incorrect")
    public JSON incorrect(@PathVariable("cardId") String cardId) throws Exception {
        Card card = authorizeCard(cardId);
        learnService.markIncorrect(card);
        return JSON.ok();
    }
}
