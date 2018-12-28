package com.lazylearn.api.service;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.Article;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.Minpair;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.indto.article.ArticleCreateIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.repo.ArticleRepo;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.DeckRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author McFly the Kid
 */
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

        sendToDeck(article.getId(), userId);
        return article;
    }

    @Transactional
    public void delete(String articleId){

        Article article = articleRepo.findOne(articleId);

        Deck deck = deckRepo.findByArticleCategoryAndUserId(article.getCategory(), article.getUserId());
        Card card = cardRepo.findByDeckIdAndFront(deck.getId(), articleId);
        cardRepo.delete(card);

        if (cardRepo.countAllByDeckId(deck.getId()) == 0){
            deckRepo.delete(deck);
        }

        articleRepo.delete(articleId);
    }

    private Deck sendToDeck(String articleId, String userId){
        final String NAME_REFIX = "Article #";
        Article article = articleRepo.findOne(articleId);

        Deck deck = deckRepo.findByArticleCategoryAndUserId(article.getCategory(), userId);
        if (deck == null){
            deck = deckService.create(NAME_REFIX + article.getCategory(), userId);
            deck.setProgramId(Consts.STEP_PROGRAM__PIMSLEUR);
            deck.setArticleCategory(article.getCategory());
            deckRepo.save(deck);
        }
        Card card = cardRepo.findByDeckIdAndFront(deck.getId(), articleId);
        if(card == null){
            card = cardService.create(articleId, Consts.DO_NOT_CHANGE, deck.getId(), userId);
            card.setArticleCategory(article.getCategory());
            cardRepo.save(card);
        } else {
            throw new AppException(401, "This article is already get");
        }

        return deck;
    }

    public BootstraptableOut searchByUserId(SearchIn in, String userId){
        List<Article> rows = articleRepo.findAllByKeywordAndUserId(in.getSearch(), userId, in.getPageable());
        Long total = articleRepo.countByKeywordAndUserId(in.getSearch(), userId);
        return new BootstraptableOut(rows, total);
    }

    public BootstraptableOut searchAll(SearchIn in){
        List<Article> rows = articleRepo.findAllByKeyword(in.getSearch(), in.getPageable());
        Long total = articleRepo.countByKeyword(in.getSearch());
        return new BootstraptableOut(rows, total);
    }
}
