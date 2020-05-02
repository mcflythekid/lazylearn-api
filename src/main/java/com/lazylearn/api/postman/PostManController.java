package com.lazylearn.api.postman;

import com.lazylearn.api.clone.service.DeckCloneService;
import com.lazylearn.api.clone.service.MinpairCloneService;
import com.lazylearn.api.clone.service.VocabCloneService;
import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author 4nha
 * Date: 2020-05-01
 */
@RestController
@RequestMapping("/postman")
public class PostManController {

    @Autowired
    private ImportService importService;

    @Autowired
    private DeckCloneService deckCloneService;

    @Autowired
    private VocabCloneService vocabCloneService;

    @Autowired
    private MinpairCloneService minpairCloneService;

    private static final String TOKEN = "dkmmcc699";

    private void validate(HttpServletRequest request){
        String token = request.getParameter("token");
        if (isBlank(token) || !token.equals(TOKEN)){
            throw new AppException(400, "Bad token");
        }
    }


    @PostMapping("/import-deck")
    public JSON importDeck(HttpServletRequest request, @RequestParam String user, @RequestParam String file) throws IOException {
        validate(request);
        return importService.importDeck(user, file);
    }

    @PostMapping("/clone-deck-to-all-user")
    public JSON cloneDeck(HttpServletRequest request, @RequestParam String deck_id) throws IOException {
        validate(request);
        deckCloneService.cloneDeck(deck_id);
        return JSON.ok("Dispatched! Don't know result");
    }

    @PostMapping("/clone-vocabdeck-to-all-user")
    public JSON cloneVocabDeck(HttpServletRequest request, @RequestParam String vocabdeck_id) throws IOException {
        validate(request);
        vocabCloneService.cloneDeck(vocabdeck_id);
        return JSON.ok("Dispatched! Don't know result");
    }

    @PostMapping("/clone-minpair-to-all-user")
    public JSON cloneMinpair(HttpServletRequest request, @RequestParam String minpair_id) throws IOException {
        validate(request);
        minpairCloneService.cloneMinpair(minpair_id);
        return JSON.ok("Dispatched! Don't know result");
    }
}
