package com.lazylearn.api.service;

import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.User;
import com.lazylearn.api.entity.Vocab;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.repo.DetailedUserRepo;
import com.lazylearn.api.repo.UserRepo;
import com.lazylearn.api.repo.VocabRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    private VocabRepo vocabRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DeckService deckService;

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

    public JSON massiveImportDeck(String templateName) throws IOException {
        final String DATE_FORMAT = "yyyyMMdd HHmmss";
        final String TEMPLATE_PREFIX = "./deck/";
        String trackingId = templateName + " " + new SimpleDateFormat(DATE_FORMAT).format(new Date());

        List<User> userList = userRepo.findAll();
        for(User user : userList){
            deckService.importDeck(TEMPLATE_PREFIX + templateName, user.getId(), trackingId);
        }
        return JSON.ok(String.format("Decks imported. Affected user: %s. Tracking id: %s", userList.size(),
                trackingId));
    }
}
