package com.lazylearn.api.service;

import com.lazylearn.api.config.env.WiredEnv;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.Vocab;
import com.lazylearn.api.indto.vocab.VocabCreateIn;
import com.lazylearn.api.indto.vocab.VocabEditIn;
import com.lazylearn.api.indto.vocab.VocabSearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.vocab.VocabEditOut;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.DeckRepo;
import com.lazylearn.api.repo.VocabRepo;
import com.lazylearn.api.vocabgenerator.VocabGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class VocabService {

    @Autowired
    private VocabRepo vocabRepo;
    @Autowired
    private DeckService deckService;
    @Autowired
    private DeckRepo deckRepo;
    @Autowired
    private FileService fileService;
    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private WiredEnv env;

    private void createCallback(Vocab vocab) throws Exception {
        for (VocabGenerator vocabGenerator : VocabGenerator.getGenerators(env.getFileUrl())) {
            String deckId = deckRepo.findByVocabdeckIdAndVocabType(vocab.getVocabdeckId(), vocabGenerator.getVocabType()).getId();
            cardRepo.save(vocabGenerator.generateCard(vocab, null, deckId));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCallback(Vocab vocab) throws Exception {
        for (VocabGenerator vocabGenerator : VocabGenerator.getGenerators(env.getFileUrl())) {
            Deck deck = deckRepo.findByVocabdeckIdAndVocabType(vocab.getVocabdeckId(), vocabGenerator.getVocabType());
            if (deck == null) {
                continue;
            }
            String deckId = deck.getId();
            Card card = cardRepo.findByDeckIdAndVocabId(deckId, vocab.getId());
            cardRepo.save(vocabGenerator.generateCard(vocab, card, deckId));
        }
    }

    private void deleteCallback(String vocabId) {
        cardRepo.deleteAllByVocabId(vocabId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Vocab create(VocabCreateIn in, String userId) throws Exception {

        Vocab vocab = new Vocab();
        BeanUtils.copyProperties(in, vocab);
        vocab.setUserId(userId);
        vocabRepo.save(vocab);

        vocab.generateAudioPath(in.getEncodedAudio());
        vocab.generateImagePath(in.getEncodedImage());
        fileService.uploadEncodedFile(vocab.getAudioPath(), in.getEncodedAudio().getContent());
        fileService.uploadEncodedFile(vocab.getImagePath(), in.getEncodedImage().getContent());
        vocabRepo.save(vocab);

        createCallback(vocab);
        return vocab;
    }

    @Transactional(rollbackFor = Exception.class)
    public VocabEditOut edit(VocabEditIn in, String userId) throws Exception {

        Vocab vocab = vocabRepo.findOne(in.getVocabId());
        BeanUtils.copyProperties(in, vocab);

        if (in.getEncodedAudio() != null) {
            vocab.generateAudioPath(in.getEncodedAudio());
            fileService.uploadEncodedFile(vocab.getAudioPath(), in.getEncodedAudio().getContent());
        }
        if (in.getEncodedImage() != null) {
            vocab.generateImagePath(in.getEncodedImage());
            fileService.uploadEncodedFile(vocab.getImagePath(), in.getEncodedImage().getContent());
        }
        vocabRepo.save(vocab);

        updateCallback(vocab);

        VocabEditOut vocabEditOut = new VocabEditOut();
        vocabEditOut.setVocab(vocab);
        vocabEditOut.setCards(cardRepo.findAllByVocabId(vocab.getId()));
        return vocabEditOut;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String vocabId) {
        vocabRepo.delete(vocabId);
        deleteCallback(vocabId);
    }

    public BootstraptableOut search(VocabSearchIn in) {
        List<Vocab> rows = vocabRepo.findAllByVocabdeckIdAndSearch(in.getVocabdeckId(), in.getSearch(), in.getPageable());
        Long total = vocabRepo.countByVocabdeckIdAndSearch(in.getVocabdeckId(), in.getSearch());
        return new BootstraptableOut(rows, total);
    }
}
