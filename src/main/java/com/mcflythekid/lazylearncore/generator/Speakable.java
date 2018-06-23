package com.mcflythekid.lazylearncore.generator;

import com.mcflythekid.lazylearncore.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class Speakable extends CardDeckGenerator {

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