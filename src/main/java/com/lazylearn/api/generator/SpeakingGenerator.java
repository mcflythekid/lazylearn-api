package com.lazylearn.api.generator;

import com.lazylearn.api.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class SpeakingGenerator extends CardDeckGenerator {

    public SpeakingGenerator(String fileRoot) {
        super(fileRoot);
    }

    @Override
    public String getPostfix() {
        return "[Speaking]";
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