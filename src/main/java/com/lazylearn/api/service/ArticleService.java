package com.lazylearn.api.service;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.Article;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.Minpair;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.indto.article.ArticleCreateIn;
import com.lazylearn.api.indto.article.ArticleRenameIn;
import com.lazylearn.api.indto.deck.DeckRenameIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.repo.ArticleRepo;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.DeckRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

        refreshCard(article.getId());
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

    @Transactional
    public Deck refreshCard(String articleId){
        Article article = articleRepo.findOne(articleId);

        Deck deck = deckRepo.findByTypeAndUserId(Consts.DECKTYPE__TOPIC, article.getUserId());
        if (deck == null) {
            deck = deckService.create("TOPIC", article.getUserId());
            deck.setType(Consts.DECKTYPE__TOPIC);
            deckRepo.save(deck);
        }

        Card card = cardRepo.findByArticleId(articleId);
        if(card == null) {
            card = cardService.create("", "", deck.getId(), article.getUserId());
            card.setArticleId(articleId);
        }

        String urlF = "<a href='/article/learn.php?type=review&id=%s&cardid=%s'>%s</a>";
        String url = String.format(urlF, article.getId(), card.getId(), article.getName());

        card.setFront(url);
        card.setBack("");

        cardRepo.save(card);

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
