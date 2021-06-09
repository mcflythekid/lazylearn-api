package com.lazylearn.api.clone.service;

import com.lazylearn.api.entity.NewUserTemplate;
import com.lazylearn.api.repo.NewUserTemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 4nha
 * Date: 2020-05-02
 */
@Service
public class CloneService {

    @Autowired
    private DeckCloneService deckCloneService;

    @Autowired
    private MinpairCloneService minpairCloneService;

    @Autowired
    private VocabCloneService vocabCloneService;

    @Autowired
    private NewUserTemplateRepo newUserTemplateRepo;

    public void cloneAll(String userId) throws Exception {
        for (NewUserTemplate template : newUserTemplateRepo.findAll()) {
            switch (template.getType()) {
                case "deck":
                    deckCloneService.cloneDeck(template.getRecordId(), userId);
                    break;
                case "vocabdeck":
                    vocabCloneService.cloneDeck(template.getRecordId(), userId);
                    break;
                case "minpair":
                    minpairCloneService.cloneMinpair(template.getRecordId(), userId);
                    break;
            }
        }
    }

    public void cloneAllAsync(String userId) {
        new Thread(() -> {
            try {
                cloneAll(userId);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
