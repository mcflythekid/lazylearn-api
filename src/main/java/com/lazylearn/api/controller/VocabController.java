package com.lazylearn.api.controller;

import com.lazylearn.api.entity.Vocab;
import com.lazylearn.api.indto.vocab.VocabCreateIn;
import com.lazylearn.api.indto.vocab.VocabEditIn;
import com.lazylearn.api.indto.vocab.VocabSearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.outdto.vocab.VocabEditOut;
import com.lazylearn.api.service.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/vocab")
public class VocabController extends BaseController {

    @Autowired
    private VocabService vocabService;

    @GetMapping("/get/{vocabId}")
    public Vocab get(@PathVariable String vocabId) throws Exception{
        return authorizeVocab(vocabId);
    }

    @PostMapping("/create")
    public Vocab create(@Valid @RequestBody VocabCreateIn in) throws Exception{
        return vocabService.create(in, getUserId());
    }

    @PostMapping("/edit")
    public VocabEditOut edit(@Valid @RequestBody VocabEditIn in) throws Exception{
        authorizeVocab(in.getVocabId());
        return vocabService.edit(in, getUserId());
    }

    @PostMapping("/delete/{vocabId}")
    public JSON delete(@PathVariable String vocabId) throws Exception{
        authorizeVocab(vocabId);
        vocabService.delete(vocabId);
        return JSON.ok("Delete success");
    }

    @PostMapping("/search")
    public BootstraptableOut search(@Valid @RequestBody VocabSearchIn in) throws Exception{
        return vocabService.search(in);
    }
}