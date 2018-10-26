package com.lazylearn.api.controller;

import com.lazylearn.api.entity.Vocabdeck;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.indto.vocabdeck.VocabdeckCreateIn;
import com.lazylearn.api.indto.vocabdeck.VocabdeckRenameIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.service.VocabdeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/vocabdeck")
public class VocabdeckController extends BaseController{

    @Autowired
    private VocabdeckService vocabdeckService;

    @GetMapping("/get/{vocabdeckId}")
    public Vocabdeck get(@PathVariable String vocabdeckId) throws Exception{
        return authorizeVocabdeck(vocabdeckId);
    }

    @PostMapping("/create")
    public Vocabdeck create(@Valid @RequestBody VocabdeckCreateIn in) throws Exception{
        return vocabdeckService.create(in, getUserId());
    }

    @PostMapping("/rename")
    public JSON rename(@Valid @RequestBody VocabdeckRenameIn in) throws Exception{
        authorizeVocabdeck(in.getVocabdeckId());
        vocabdeckService.rename(in);
        return JSON.ok("Rename success");
    }

    @PostMapping("/delete/{vocabdeckId}")
    public JSON delete(@PathVariable String vocabdeckId) throws Exception{
        authorizeVocabdeck(vocabdeckId);
        vocabdeckService.delete(vocabdeckId);
        return JSON.ok("Delete success");
    }

    @PostMapping("/archive/{vocabdeckId}")
    public JSON archive(@PathVariable String vocabdeckId) throws Exception{
        authorizeVocabdeck(vocabdeckId);
        vocabdeckService.archive(vocabdeckId);
        return JSON.ok("Archive success");
    }

    @PostMapping("/unarchive/{vocabdeckId}")
    public JSON unarchive(@PathVariable String vocabdeckId) throws Exception{
        authorizeVocabdeck(vocabdeckId);
        vocabdeckService.unarchive(vocabdeckId);
        return JSON.ok("Unarchive success");
    }

    @PostMapping("/search")
    public BootstraptableOut search(@Valid @RequestBody SearchIn in) throws Exception{
        return vocabdeckService.search(in, getUserId());
    }
}