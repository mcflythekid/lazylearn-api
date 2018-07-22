package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.config.exception.AppException;
import com.mcflythekid.lazylearncore.generator.CardDeckGenerator;
import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Vocab;
import com.mcflythekid.lazylearncore.indto.vocab.VocabCreateIn;
import com.mcflythekid.lazylearncore.indto.vocab.VocabEditIn;
import com.mcflythekid.lazylearncore.indto.vocab.VocabSearchIn;
import com.mcflythekid.lazylearncore.outdto.BootstraptableOut;
import com.mcflythekid.lazylearncore.outdto.vocab.VocabEditOut;
import com.mcflythekid.lazylearncore.repo.CardRepo;
import com.mcflythekid.lazylearncore.repo.DeckRepo;
import com.mcflythekid.lazylearncore.repo.VocabRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Value("${file-root}")
    private String fileRoot;

    @Transactional(rollbackFor = Exception.class)
    public void createCallback(Vocab vocab) throws Exception {
        for (CardDeckGenerator cardDeckGenerator : CardDeckGenerator.getGenerators(fileRoot)){
            String deckId = deckRepo.findByVocabdeckIdAndVocabType(vocab.getVocabdeckId(), cardDeckGenerator.getVocabType()).getId();
            cardRepo.save(cardDeckGenerator.generateCard(vocab, null, deckId));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCallback(Vocab vocab) throws Exception {
        for (CardDeckGenerator cardDeckGenerator : CardDeckGenerator.getGenerators(fileRoot)){
            String deckId = deckRepo.findByVocabdeckIdAndVocabType(vocab.getVocabdeckId(), cardDeckGenerator.getVocabType()).getId();
            Card card = cardRepo.findByDeckIdAndVocabId(deckId, vocab.getId());
            cardRepo.save(cardDeckGenerator.generateCard(vocab, card, deckId));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCallback(String vocabId){
        cardRepo.deleteAllByVocabId(vocabId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Vocab create(VocabCreateIn in, String userId) throws Exception {

        if (vocabRepo.findByWordAndUserId(in.getWord(), userId) != null){
            throw new AppException(HttpStatus.CONFLICT.value(), in.getWord() + " is already existed");
        }

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
    public VocabEditOut edit(VocabEditIn in, String userId) throws Exception{

        if (vocabRepo.findByIdNotAndWordAndUserId(in.getVocabId(), in.getWord(), userId) != null){
            throw new AppException(HttpStatus.CONFLICT.value(), in.getWord() + " is already existed");
        }

        Vocab vocab = vocabRepo.findOne(in.getVocabId());
        BeanUtils.copyProperties(in, vocab);

        if(in.getEncodedAudio() != null){
            vocab.generateAudioPath(in.getEncodedAudio());
            fileService.uploadEncodedFile(vocab.getAudioPath(), in.getEncodedAudio().getContent());
        }
        if(in.getEncodedImage() != null){
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
    public void delete(String vocabId){
        vocabRepo.delete(vocabId);
        deleteCallback(vocabId);
    }

    public BootstraptableOut search(VocabSearchIn in){
        List<Vocab> rows = vocabRepo.findAllByVocabdeckIdAndSearch(in.getVocabdeckId(), in.getSearch(), in.getPageable());
        Long total = vocabRepo.countByVocabdeckIdAndSearch(in.getVocabdeckId(), in.getSearch());
        return new BootstraptableOut(rows, total);
    }
}
