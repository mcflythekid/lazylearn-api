package com.lazylearn.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lazylearn.api.entity.Article;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.indto.article.ArticleCreateIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.service.ArticleService;

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

    @PostMapping("/search")
    public BootstraptableOut search(@RequestBody SearchIn in) throws Exception {
        return articleService.searchByUserId(in, getUserId());
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
