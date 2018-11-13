package com.lazylearn.api.controller;

import com.lazylearn.api.indto.BasicIn;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.service.AdminService;
import com.lazylearn.api.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/massive-import-deck")
    public JSON massiveImportDeck(@Valid @RequestBody BasicIn payload) throws Exception {
        return adminService.massiveImportDeck(payload.getData());
    }
}