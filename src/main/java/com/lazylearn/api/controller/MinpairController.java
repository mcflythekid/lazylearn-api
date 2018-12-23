package com.lazylearn.api.controller;

import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.Minpair;
import com.lazylearn.api.indto.minpair.MinpairCreateIn;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.service.MinpairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/minpair")
public class MinpairController extends BaseController{

    @Autowired
    private MinpairService minpairService;

    @PostMapping("/create")
    public Object create(@Valid @RequestBody MinpairCreateIn in) throws Exception{
        return minpairService.create(in, getUserId());
    }

    @PostMapping("/delete/{minpairId}")
    public JSON delete(@PathVariable String minpairId) throws Exception{
        minpairService.delete(minpairId);
        return JSON.ok("Delete success");
    }

    @PostMapping("/send-to-deck/{minpairId}")
    public Deck sendToDeck(@PathVariable String minpairId) throws Exception {
        return minpairService.sendToDeck(minpairId, getUserId());
    }

    @PostMapping("/search")
    public BootstraptableOut searchByUserId(@RequestBody SearchIn in) throws Exception {
        return minpairService.searchByKeywordAndUserId(in, getUserId());
    }

    @PostMapping("/admin/search")
    public BootstraptableOut searchAll(@RequestBody SearchIn in) throws Exception {
        return minpairService.searchByKeyword(in);
    }

    @GetMapping("/get/{minpairId}")
    public Minpair create(@PathVariable String minpairId) throws Exception{
        return authorizeMinpair(minpairId);
    }
}
