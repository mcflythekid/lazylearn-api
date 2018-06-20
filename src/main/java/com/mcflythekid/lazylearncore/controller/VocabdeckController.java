package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Vocabdeck;
import com.mcflythekid.lazylearncore.indto.SearchIn;
import com.mcflythekid.lazylearncore.indto.vocabdeck.VocabdeckCreateIn;
import com.mcflythekid.lazylearncore.indto.vocabdeck.VocabdeckRenameIn;
import com.mcflythekid.lazylearncore.outdto.BootstraptableOut;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.service.VocabdeckService;
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