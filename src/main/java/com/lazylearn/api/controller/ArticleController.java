package com.lazylearn.api.controller;

import javax.validation.Valid;

import com.google.common.collect.ImmutableMap;
import com.lazylearn.api.indto.article.ArticleRenameIn;
import com.lazylearn.api.outdto.RandomTopicDto;
import com.lazylearn.api.repo.ArticleRepo;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController{

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepo articleRepo;

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

    @PostMapping("/rename")
    public JSON rename(@Valid @RequestBody ArticleRenameIn payload) throws Exception{
        authorizeArticle(payload.getArticleId());
        articleService.rename(payload);
        return JSON.ok("Rename success");
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

    @GetMapping("/public/{slug}")
    public Map publicTopicRead(@PathVariable String slug){
        List<Article> articleList = articleRepo.findAllBySlug(slug);
        if (articleList.isEmpty()){
            return null;
        }
        return ImmutableMap.of("name", articleList.get(0).getName(), "content", articleList.get(0).getContent());
    }

    @GetMapping("/public/randoms/{slug}")
    public List<RandomTopicDto> publicRandoms(@PathVariable String slug){
        int size = 4;
        List<Article> selected = null;
        List<Article> articleList = articleRepo.findAllSlugAndName();
        Collections.shuffle(articleList);
        if (articleList.size() <= size){
            selected = articleList;
        } else {
            selected = articleList.subList(0, size);
        }

        List<RandomTopicDto> output = new ArrayList<>();
        for (Article article : selected){
            if (article.getSlug().equals(slug)){
                continue;
            }
            output.add(RandomTopicDto.builder()
                .name(article.getName())
                .slug(article.getSlug())
            .build());
        }
        return output;
    }
}
