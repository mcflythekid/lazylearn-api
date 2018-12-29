package com.lazylearn.api.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lazylearn.api.entity.*;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.repo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author McFly the Kid
 */
@Service
public class AdminService {

    @Autowired
    private DetailedUserRepo detailedUserRepo;

    @Autowired
    private VocabService vocabService;

    @Autowired
    private VocabdeckService vocabdeckService;

    @Autowired
    private VocabRepo vocabRepo;

    @Autowired
    private VocabdeckRepo vocabdeckRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DeckService deckService;

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MinpairRepo minpairRepo;

    @Autowired
    private MinpairService minpairService;

    public BootstraptableOut search(SearchIn in){
        List rows = detailedUserRepo.findAllByEmail(in.getSearch(), in.getPageable());
        Long total = detailedUserRepo.countAllByEmail(in.getSearch());
        return new BootstraptableOut(rows, total);
    }

    public JSON refreshAllVocab() throws Exception{
        final int SIZE = 50;
        long count = vocabRepo.count();
        Double totalPage = Math.ceil(count * 1.0 / SIZE);
        for (int page = 0; page < totalPage; page++){
            List<Vocab> vocabs = vocabRepo.findAll(new PageRequest(page, SIZE)).getContent();
            for (Vocab vocab : vocabs){
                vocabService.updateCallback(vocab);
            }
        }

        return JSON.ok(count + " Vocabs refreshed");
    }

    public JSON refreshAllVocabdeck() throws Exception{
        final int SIZE = 50;
        long count = vocabdeckRepo.count();
        Double totalPage = Math.ceil(count * 1.0 / SIZE);
        for (int page = 0; page < totalPage; page++){
            List<Vocabdeck> vocabdecks = vocabdeckRepo.findAll(new PageRequest(page, SIZE)).getContent();
            for (Vocabdeck vocabdeck : vocabdecks){
                vocabdeckService.updateCallback(vocabdeck);
            }
        }

        return JSON.ok(count + " Vocabdecks refreshed");
    }

    public JSON refreshAllTopic() throws Exception{
        final int SIZE = 50;
        long count = articleRepo.count();
        Double totalPage = Math.ceil(count * 1.0 / SIZE);
        for (int page = 0; page < totalPage; page++){
            List<Article> topics = articleRepo.findAll(new PageRequest(page, SIZE)).getContent();
            for (Article topic : topics){
                articleService.refreshCard(topic.getId());
            }
        }

        return JSON.ok(count + " Topics refreshed");
    }

    public JSON refreshAllMinpair() throws Exception{
        final int SIZE = 50;
        long count = minpairRepo.count();
        Double totalPage = Math.ceil(count * 1.0 / SIZE);
        for (int page = 0; page < totalPage; page++){
            List<Minpair> minpairs = minpairRepo.findAll(new PageRequest(page, SIZE)).getContent();
            for (Minpair minpair : minpairs){
                minpairService.updateCardAndDeckCallBack(minpair.getId());
            }
        }

        return JSON.ok(count + " Minpairs refreshed");
    }

    public JSON massiveImportDeck(String templateName) throws IOException {
        final String DATE_FORMAT = "yyyyMMdd HHmmss";
        String trackingId = templateName + " " + new SimpleDateFormat(DATE_FORMAT).format(new Date());

        List<User> userList = userRepo.findAll();
        for(User user : userList){
            deckService.importDeck(templateName, user.getId(), trackingId);
        }
        return JSON.ok(String.format("Decks imported. Affected user: %s. Tracking id: %s", userList.size(),
                trackingId));
    }
}
