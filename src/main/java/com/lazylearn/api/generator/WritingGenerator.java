package com.lazylearn.api.generator;

import com.lazylearn.api.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class WritingGenerator extends CardDeckGenerator {

    public WritingGenerator(String fileRoot) {
        super(fileRoot);
    }

    @Override
    public String getPrefix() {
        return "[Writing]";
    }

    @Override
    public Integer getVocabType() {
        return VOCAB_TYPE__WRITING;
    }

    @Override
    public String getCardFrontTemplate() throws IOException {
        return ResouresUtils.read("./template/writing-front.html");
    }

    @Override
    public String getCardBackTemplate() throws IOException {
        return ResouresUtils.read("./template/writing-back.html");
    }
}