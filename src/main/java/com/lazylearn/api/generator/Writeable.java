package com.lazylearn.api.generator;

import com.lazylearn.api.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class Writeable extends CardDeckGenerator {

    public Writeable(String fileRoot) {
        super(fileRoot);
    }

    @Override
    public String getPostfix() {
        return "[Writeable]";
    }

    @Override
    public Integer getVocabType() {
        return VOCAB_TYPE__WRITEABLE;
    }

    @Override
    public String getCardFrontTemplate() throws IOException {
        return ResouresUtils.read("./template/writeable-front.html");
    }

    @Override
    public String getCardBackTemplate() throws IOException {
        return ResouresUtils.read("./template/writeable-back.html");
    }
}