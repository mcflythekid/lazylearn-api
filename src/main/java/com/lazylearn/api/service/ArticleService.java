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
        return articleRepo.save(article);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String articleId){
        articleRepo.delete(articleId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Deck sendToDeck(String articleId, String userId) throws Exception{
        final String NAME_REFIX = "Article #";
        Article article = articleRepo.findOne(articleId);

        Deck deck = deckRepo.findByArticleCategoryAndUserId(article.getCategory(), userId);
        if (deck == null){
            deck = deckService.create(NAME_REFIX + article.getCategory(), userId);
            deck.setProgramId(Consts.STEP_PROGRAM__EFFORTLESS);
            deck.setArticleCategory(article.getCategory());
            deckRepo.save(deck);
        }
        Card card = cardRepo.findByDeckIdAndFront(deck.getId(), articleId);
        if(card == null){
            card = cardService.create(articleId, "", deck.getId(), userId);
            card.setArticleCategory(article.getCategory());
            cardRepo.save(card);
        } else {
            throw new AppException(401, "This article is already get");
        }

        return deck;
    }

    public BootstraptableOut search(SearchIn in, String userId){
        List<Article> rows = articleRepo.findAllByUserIdAndSearch(userId, in.getSearch(), in.getPageable());
        Long total = articleRepo.countByUserIdAndSearch(userId, in.getSearch());
        return new BootstraptableOut(rows, total);
    }

    public BootstraptableOut searchAll(SearchIn in){
        List<Article> rows = articleRepo.findAllByKeyword(in.getSearch(), in.getPageable());
        Long total = articleRepo.countByKeyword(in.getSearch());
        return new BootstraptableOut(rows, total);
    }
}
