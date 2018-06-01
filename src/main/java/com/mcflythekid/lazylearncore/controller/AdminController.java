package com.mcflythekid.lazylearncore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @GetMapping("/test")
    public Object archiveUser(String userId){
        return "hello man";
    }

    public Object unarchiveUser(String userId){
        return null;
    }

    public Object listUser(){
        return null;
    }
}
