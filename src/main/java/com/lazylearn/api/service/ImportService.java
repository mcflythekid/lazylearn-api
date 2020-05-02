package com.lazylearn.api.service;

import com.lazylearn.api.config.env.WiredEnv;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.User;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author 4nha
 * Date: 2020-05-01
 */
@Service
public class ImportService {

    @Autowired
    private DeckService deckService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WiredEnv wiredEnv;

    public JSON importDeck(String userIdentity, String filePath) throws IOException {
        User user = userRepo.findByEmail(userIdentity);
        if (user == null){
            user = userRepo.findOne(userIdentity);
        }
        if (user == null){
            return JSON.error("User not found");
        }

        Deck deck = deckService.importDeckFromFile(wiredEnv.getFileUpload() + filePath, user.getId(), "");
        JSON json = JSON.ok("Create success");
        return json;
    }
}
