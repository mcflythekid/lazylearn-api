package com.lazylearn.api.vocabgenerator;

import com.lazylearn.api.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class SpeakingVocabGenerator extends VocabGenerator {

    public SpeakingVocabGenerator(String fileRoot) {
        super(fileRoot);
    }

    @Override
    public String getPrefix() {
        return "[Speaking Vocab]";
    }

    @Override
    public Integer getVocabType() {
        return VOCAB_TYPE__SPEAKING;
    }

    @Override
    public String getCardFrontTemplate() throws IOException {
        return ResouresUtils.read("./template/speaking-front.html");
    }

    @Override
    public String getCardBackTemplate() throws IOException {
        return ResouresUtils.read("./template/speaking-back.html");
    }
}