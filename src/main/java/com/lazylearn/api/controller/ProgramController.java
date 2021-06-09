package com.lazylearn.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/program")
public class ProgramController {

    private static Map<String, String> ALL_PROGRAM = new HashMap<>();

    static {
        ALL_PROGRAM.put("pimsleur", "Pimsleur");
        ALL_PROGRAM.put("sm2", "SuperMemo2");
    }

    @GetMapping("/all")
    public Object getAll() {
        return ALL_PROGRAM;
    }

}
