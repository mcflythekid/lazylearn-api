package com.lazylearn.api.clone.service;

import com.lazylearn.api.entity.User;
import com.lazylearn.api.entity.Vocab;
import com.lazylearn.api.entity.Vocabdeck;
import com.lazylearn.api.indto.vocab.VocabCreateIn;
import com.lazylearn.api.indto.vocabdeck.VocabdeckCreateIn;
import com.lazylearn.api.repo.UserRepo;
import com.lazylearn.api.repo.VocabRepo;
import com.lazylearn.api.repo.VocabdeckRepo;
import com.lazylearn.api.service.FileService;
import com.lazylearn.api.service.VocabService;
import com.lazylearn.api.service.VocabdeckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author 4nha
 * Date: 2020-05-02
 */
@Service
@Slf4j
public class VocabCloneService {

    @Autowired
    private VocabdeckService vocabdeckService;
    @Autowired
    private VocabService vocabService;

    @Autowired
    private VocabdeckRepo vocabdeckRepo;
    @Autowired
    private VocabRepo vocabRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FileService fileService;


    private VocabdeckCreateIn createDeckDto(String oldDeckId){
        Vocabdeck deck = vocabdeckRepo.findOne(oldDeckId);

        VocabdeckCreateIn deckCreateIn = new VocabdeckCreateIn();
        deckCreateIn.setName(deck.getName());

        return deckCreateIn;
    }

    private List<VocabCreateIn> createVocabDtoList(String oldDeckId, String newDeckId) throws IOException {
        List<Vocab> vocabs = vocabRepo.findAllByVocabdeckId(oldDeckId);

        List<VocabCreateIn> vocabCreateInList = new ArrayList<>();
        for(Vocab vocab : vocabs){
            VocabCreateIn vocabCreateIn = new VocabCreateIn();

            vocabCreateIn.setEncodedAudio(fileService.readFileFromPath(vocab.getAudioPath()));
            vocabCreateIn.setEncodedImage(fileService.readFileFromPath(vocab.getImagePath()));

            vocabCreateIn.setPersonalConnection(vocab.getPersonalConnection());
            vocabCreateIn.setPhonetic(vocab.getPhonetic());
            vocabCreateIn.setPhrase(vocab.getPhrase());
            vocabCreateIn.setWord(vocab.getWord());

            vocabCreateIn.setVocabdeckId(newDeckId);

            vocabCreateInList.add(vocabCreateIn);
        }

        return vocabCreateInList;
    }

    @Transactional
    public void cloneDeck(String oldDeckId, String userId) throws Exception {
        Vocabdeck oldDeck = vocabdeckRepo.findOne(oldDeckId);
        if (isBlank(oldDeck.getCloneableid())){
            throw new RuntimeException("Cannot clone because this vocabdeck does not have cloneableid");
        }
        if (vocabdeckRepo.countByUserIdAndCloneableid(userId, oldDeck.getCloneableid()) > 0){
            log.info("Skip because already imported");
            return;
        }

        Vocabdeck newDeck = vocabdeckService.create(createDeckDto(oldDeckId), userId);
        newDeck.setCloneableid(oldDeck.getCloneableid());
        vocabdeckRepo.save(newDeck);

        for (VocabCreateIn vocabCreateIn : createVocabDtoList(oldDeckId, newDeck.getId())){
            vocabService.create(vocabCreateIn, userId);
        }
    }

    @Transactional
    public void cloneDeck(String oldDeckId){
        for (User user : userRepo.findAll()){
            try {
                cloneDeck(oldDeckId, user.getUserId());
            } catch (Exception e){
                log.error("Cannot clone for user: {}", user.getEmail());
                log.error("Cannot clone for user", e);
            }
        }
    }
}
