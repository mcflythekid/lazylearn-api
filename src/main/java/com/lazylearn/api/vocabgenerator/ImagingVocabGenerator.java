package com.lazylearn.api.vocabgenerator;

import com.lazylearn.api.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class ImagingVocabGenerator extends VocabGenerator {

    public ImagingVocabGenerator(String fileRoot) {
        super(fileRoot);
    }

    @Override
    public String getPrefix() {
        return "[Connection Vocab]";
    }

    @Override
    public Integer getVocabType() {
        return VOCAB_TYPE__IMAGING;
    }

    @Override
    public String getCardFrontTemplate() throws IOException {
        return ResouresUtils.read("./template/imaging-front.html");
    }

    @Override
    public String getCardBackTemplate() throws IOException {
        return ResouresUtils.read("./template/imaging-back.html");
    }
}