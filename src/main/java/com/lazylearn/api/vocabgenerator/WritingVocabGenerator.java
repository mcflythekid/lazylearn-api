package com.lazylearn.api.vocabgenerator;

import com.lazylearn.api.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class WritingVocabGenerator extends VocabGenerator {

    public WritingVocabGenerator(String fileRoot) {
        super(fileRoot);
    }

    @Override
    public String getPrefix() {
        return "[Writing Vocab]";
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