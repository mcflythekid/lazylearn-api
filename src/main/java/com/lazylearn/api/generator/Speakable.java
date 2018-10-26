package com.lazylearn.api.generator;

import com.lazylearn.api.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class Speakable extends CardDeckGenerator {

    public Speakable(String fileRoot) {
        super(fileRoot);
    }

    @Override
    public String getPostfix() {
        return "[Speakable]";
    }

    @Override
    public Integer getVocabType() {
        return VOCAB_TYPE__SPEAKABLE;
    }

    @Override
    public String getCardFrontTemplate() throws IOException {
        return ResouresUtils.read("/template/speakable-front.html");
    }

    @Override
    public String getCardBackTemplate() throws IOException {
        return ResouresUtils.read("/template/speakable-back.html");
    }
}