package com.lazylearn.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lazylearn.api.indto.BasicIn;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.service.AdminService;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/search-user")
    public BootstraptableOut searchUser(@RequestBody SearchIn in){
        return adminService.search(in);
    }

    @PostMapping("/refresh-all-vocab")
    public JSON refreshAllVocab() throws Exception {
        return adminService.refreshAllVocab();
    }

    @PostMapping("/refresh-all-vocabdeck")
    public JSON refreshAllVocabdeck() throws Exception {
        return adminService.refreshAllVocabdeck();
    }

    @PostMapping("/refresh-all-topic")
    public JSON refreshAllTopic() throws Exception {
        return adminService.refreshAllTopic();
    }

    @PostMapping("/refresh-all-minpair")
    public JSON refreshAllMinpair() throws Exception {
        return adminService.refreshAllMinpair();
    }

    @PostMapping("/massive-import-deck")
    public JSON massiveImportDeck(@Valid @RequestBody BasicIn payload) throws Exception {
        return adminService.massiveImportDeck(payload.getData());
    }
}