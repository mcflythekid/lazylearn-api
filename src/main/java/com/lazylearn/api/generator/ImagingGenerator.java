package com.lazylearn.api.generator;

import com.lazylearn.api.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class ImagingGenerator extends CardDeckGenerator {

    public ImagingGenerator(String fileRoot) {
        super(fileRoot);
    }

    @Override
    public String getPrefix() {
        return "[Imaging]";
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