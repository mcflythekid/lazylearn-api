package com.lazylearn.api.service;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.entity.VocabSaved;
import com.lazylearn.api.repo.VocabSavedRepo;
import com.lazylearn.api.unit.OxfordUnit;
import com.lazylearn.api.unit.VocabSampleDto;
import com.lazylearn.api.util.StringUtils2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author 4nha
 * Date: 2020-05-13
 */
@Service
@Slf4j
public class VocabSavedService {

    @Autowired
    private VocabSavedRepo vocabSavedRepo;

    @Autowired
    private OxfordUnit oxfordUnit;

    @Autowired
    private FileService fileService;

    public VocabSampleDto getSampleByWords(String words, int languageId) throws Exception {
        // Validate language
        if (languageId != Consts.VocabTemplate.LANGUAGE_ENGLISH) {
            throw Consts.VocabTemplate.LANG_NOT_FOUND;
        }

        // Validate words
        String validatedWords = StringUtils2.removeTabAndDoubleSpaceAndTrim(words);
        if (isBlank(validatedWords)) {
            throw Consts.VocabTemplate.INVALID_WORD;
        }

        // Process single word & Validate sub-words
        String[] validatedWordsArray = validatedWords.split("\\s");
        if (validatedWordsArray.length == 1) {
            return getSampleByWord(validatedWordsArray[0], languageId);
        }
        if (validatedWordsArray.length > Consts.VocabTemplate.WORD_LIMIT) {
            throw Consts.VocabTemplate.TOO_MANY_WORD;
        }

        // Merge words
        List<VocabSampleDto> oxfordDtoList = new ArrayList<>();
        for (String word : validatedWordsArray) {
            oxfordDtoList.add(getSampleByWord(word, languageId));
        }
        String word = "";
        String phonetic = "";
        for (VocabSampleDto vocabSampleDto : oxfordDtoList) {
            word += vocabSampleDto.getWord().trim() + " ";
            phonetic += vocabSampleDto.getPhonetic().replaceAll("\\/", "").trim() + " ";
        }
        word = word.trim();
        phonetic = "/" + phonetic.trim() + "/";

        return VocabSampleDto.builder()
                .word(word)
                .phonetic(phonetic)
                .build();
    }


    private VocabSampleDto getSampleByWord(String word, int languageId) throws Exception {
        // Get from saved cache first
        VocabSaved vocabSaved = vocabSavedRepo.findByWordAndLanguageId(word, languageId);
        if (vocabSaved != null) {
            log.info("Get word '{}' from cache", word);
            return VocabSampleDto
                    .builder()
                    .word(vocabSaved.getWord())
                    .phonetic(vocabSaved.getPhonetic())
                    .phrase(vocabSaved.getPhrase())
                    .audio64(fileService.readFileFromPath(vocabSaved.getAudioPath()).getContent())
                    .build();
        }

        // Get word from internet
        log.info("Get word '{}' from internet", word);
        VocabSampleDto downloadedSample = oxfordUnit.crawlSingle(word);

        // Save to cache
        VocabSaved newSavedVocab = new VocabSaved();
        newSavedVocab.setCreatedBy(Consts.User.SYSTEM);
        newSavedVocab.setWord(word);
        newSavedVocab.setPhrase(downloadedSample.getPhrase());
        newSavedVocab.setPhonetic(downloadedSample.getPhonetic());
        newSavedVocab.setLanguageId(Consts.VocabTemplate.LANGUAGE_ENGLISH);
        newSavedVocab.setPlatformId(Consts.VocabTemplate.PLATFORM_OXFORD_LEARNER);
        vocabSavedRepo.save(newSavedVocab);
        //       Upload
        newSavedVocab.generateAudioPath(downloadedSample.getEncodedAudioFile());
        fileService.uploadEncodedFile(newSavedVocab.getAudioPath(), downloadedSample.getEncodedAudioFile().getContent());
        vocabSavedRepo.save(newSavedVocab);

        return downloadedSample;
    }
}
