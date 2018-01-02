package com.mcflythekid.lazylearncore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@RequestMapping("/login")
    public String login() {
        return "greeting in";
    }
	
	@RequestMapping("/logout")
    public String logout() {
        return "greeting out";
    }
}
