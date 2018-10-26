package com.lazylearn.api.generator;

import com.lazylearn.api.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class KnowWord extends CardDeckGenerator {

    protected KnowWord(String fileRoot) {
        super(fileRoot);
    }

    @Override
    public String getPostfix() {
        return "[Know Word]";
    }

    @Override
    public Integer getVocabType() {
        return VOCAB_TYPE__KNOW_WORD;
    }

    @Override
    public String getCardFrontTemplate() throws IOException {
        return ResouresUtils.read("/template/knowWord-front.html");
    }

    @Override
    public String getCardBackTemplate() throws IOException {
        return ResouresUtils.read("/template/knowWord-back.html");
    }
}