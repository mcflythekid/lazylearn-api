package com.lazylearn.api.controller;

import com.lazylearn.api.entity.Article;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.Minpair;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.indto.article.ArticleCreateIn;
import com.lazylearn.api.indto.minpair.MinpairCreateIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.service.ArticleService;
import com.lazylearn.api.service.MinpairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController{

    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    public Object create(@Valid @RequestBody ArticleCreateIn in) throws Exception{
        return articleService.create(in, getUserId());
    }

    @PostMapping("/delete/{articleId}")
    public JSON delete(@PathVariable String articleId) throws Exception{
        authorizeArticle(articleId);
        articleService.delete(articleId);
        return JSON.ok("Delete success");
    }

    @PostMapping("/send-to-deck/{articleId}")
    public Deck sendToDeck(@PathVariable String articleId) throws Exception {
        return articleService.sendToDeck(articleId, getUserId());
    }

    @PostMapping("/search")
    public BootstraptableOut search(@RequestBody SearchIn in) throws Exception {
        return articleService.search(in, getUserId());
    }

    @PostMapping("/admin/search")
    public BootstraptableOut adminSearch(@RequestBody SearchIn in) throws Exception {
        return articleService.searchAll(in);
    }

    @GetMapping("/get/{articleId}")
    public Article create(@PathVariable String articleId) throws Exception{
        return authorizeArticle(articleId);
    }
}
