package com.lazylearn.api.controller;

import com.lazylearn.api.entity.MinpairFile;
import com.lazylearn.api.indto.minpair.MinpairFileCreateIn;
import com.lazylearn.api.indto.minpair.MinpairFileRenameIn;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.service.MinpairFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/minpair-file")
public class MinpairFileController extends BaseController {

    @Autowired
    private MinpairFileService minpairFileService;

    @PostMapping("/upload")
    public MinpairFile upload(@Valid @RequestBody MinpairFileCreateIn in) throws Exception {
        authorizeMinpairFile(in.getMinpairId());
        return minpairFileService.upload(in);
    }

    @PostMapping("/delete/{minpairFileId}")
    public JSON delete(@PathVariable String minpairFileId) throws Exception {
        minpairFileService.delete(authorizeMinpairFile(minpairFileId));
        return JSON.ok("Delete success");
    }

    @PostMapping("/rename/{minpairFileId}")
    public MinpairFile rename(@Valid @RequestBody MinpairFileRenameIn in, @PathVariable String minpairFileId) throws Exception {
        return minpairFileService.rename(authorizeMinpairFile(minpairFileId), in.getNewName());
    }
}
