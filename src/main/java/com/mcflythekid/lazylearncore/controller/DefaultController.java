package com.mcflythekid.lazylearncore.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcflythekid.lazylearncore.Const;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author McFly the Kid
 */
@RestController
public class DefaultController {

    @GetMapping("/")
    public Object get(){
        return new Date().toString();
    }

    @GetMapping("/cc")
    public Object getcc(){
        return new Date().toString();
    }
}
