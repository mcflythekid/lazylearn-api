package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.outdto.LearnOutDto;
import com.mcflythekid.lazylearncore.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author McFly the Kid
 */
@RestController
public class LearnController extends BaseController{

    @Autowired
    private LearnService learnService;

    @GetMapping("/learn/{deckId}/by-review")
    public LearnOutDto getByReview(@PathVariable("deckId") String deckId){
        Deck deck = authorizeDeck(deckId);

        return learnService.getByReview(deck);
    }

    @GetMapping("/learn/{deckId}/by-learn")
    public LearnOutDto getByLearn(@PathVariable("deckId") String deckId){
        Deck deck = authorizeDeck(deckId);

        return learnService.getByLearn(deck);
    }
}
