package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.indto.DeckCreateInDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.service.AuthService;
import com.mcflythekid.lazylearncore.service.DeckService;
import com.mcflythekid.lazylearncore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
public class DeckController {

    @PostMapping("/user/{userId}/deck")
    public JSON createDeck(@Valid @RequestBody DeckCreateInDto deckCreateInDto,
                           @PathVariable("userId") String userId){
        User user = userService.findOne(userId);
        authService.checkOwner(user);
        return deckService.create(deckCreateInDto, user);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private DeckService deckService;
}
