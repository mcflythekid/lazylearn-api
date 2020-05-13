package com.lazylearn.api.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.*;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.repo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import static com.lazylearn.api.config.Consts.DECKTYPE__DEFAULT;

/**
 * @author McFly the Kid
 */
@Service
public class AdminService {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

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

    @Autowired
    private DeckRepo deckRepo;

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

    public JSON transferCards(String fromdeck, String todeck) {
        Deck from = deckRepo.findOne(fromdeck);
        Deck to = deckRepo.findOne(todeck);

        if (from == null || to == null){
            throw new AppException(404, "Decks not found");
        }

        if (!from.getType().equalsIgnoreCase(DECKTYPE__DEFAULT) || !to.getType().equalsIgnoreCase(DECKTYPE__DEFAULT)){
            throw new AppException(403, "Transfer extra deck (not default) is prohibited");
        }

        if (!from.getUserId().equalsIgnoreCase(to.getUserId())){
            throw new AppException(403, "Transfer across user is prohibited");
        }

        int rows = jdbcTemplate.update("update card set deckid = :to where deckid = :from",
                new MapSqlParameterSource().addValue("from", fromdeck).addValue("to", todeck));

        return JSON.ok(rows + " Cards transfered");
    }
}
