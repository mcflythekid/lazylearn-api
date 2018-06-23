package com.mcflythekid.lazylearncore.generator;

import com.mcflythekid.lazylearncore.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class KnowImage extends CardDeckGenerator {

    public KnowImage(String fileRoot) {
        super(fileRoot);
    }

    @Override
    public String getPostfix() {
        return "[Know Image]";
    }

    @Override
    public Integer getVocabType() {
        return VOCAB_TYPE__KNOW_IMAGE;
    }

    @Override
    public String getCardFrontTemplate() throws IOException {
        return ResouresUtils.read("/template/knowImage-front.html");
    }

    @Override
    public String getCardBackTemplate() throws IOException {
        return ResouresUtils.read("/template/knowImage-back.html");
    }
}