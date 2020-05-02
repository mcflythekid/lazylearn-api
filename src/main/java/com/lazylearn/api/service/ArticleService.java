package com.lazylearn.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lazylearn.api.config.Consts;
import com.lazylearn.api.entity.Article;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.indto.article.ArticleCreateIn;
import com.lazylearn.api.indto.article.ArticleRenameIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.repo.ArticleRepo;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.DeckRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Slf4j
@Service
public class ArticleService {

    @Autowired
    private FileService fileService;
    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private DeckRepo deckRepo;
    @Autowired
    private DeckService deckService;
    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepo cardRepo;

    @Transactional(rollbackFor = Exception.class)
    public Article create(ArticleCreateIn in, String userId){
        Article article = new Article();
        article.setUserId(userId);
        BeanUtils.copyProperties(in, article);
        article = articleRepo.save(article);

        return article;
    }

    @Transactional(rollbackFor = Exception.class)
    public void rename(ArticleRenameIn in) {
        Article article = articleRepo.findOne(in.getArticleId());
        article.setName(in.getNewName());
        articleRepo.save(article);
    }

    @Transactional
    public void delete(String articleId){

        Article article = articleRepo.findOne(articleId);

        Card card = cardRepo.findByArticleId(articleId);
        if (card != null) {
            cardRepo.delete(card);
        }

        Deck deck = deckRepo.findByTypeAndUserId(Consts.DECKTYPE__TOPIC, article.getUserId());
        if (deck != null && cardRepo.countAllByDeckId(deck.getId()) == 0) {
            deckRepo.delete(deck);
        }

        articleRepo.delete(articleId);
    }

    public BootstraptableOut searchByUserId(SearchIn in, String userId){
        List<Article> rows = articleRepo.findAllByKeywordAndUserId(in.getSearch(), userId, in.getPageable());
        try {
            System.out.println(new ObjectMapper().writeValueAsString(rows));
            log.info(new ObjectMapper().writeValueAsString(rows));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Long total = articleRepo.countByKeywordAndUserId(in.getSearch(), userId);
        return new BootstraptableOut(rows, total);
    }

    public BootstraptableOut searchAll(SearchIn in){
        List<Article> rows = articleRepo.findAllByKeyword(in.getSearch(), in.getPageable());
        try {
            System.out.println(new ObjectMapper().writeValueAsString(rows));
            log.info(new ObjectMapper().writeValueAsString(rows));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Long total = articleRepo.countByKeyword(in.getSearch());
        return new BootstraptableOut(rows, total);
    }
}
