package com.mcflythekid.lazylearncore.generator;

import com.mcflythekid.lazylearncore.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class Writeable extends CardDeckGenerator {

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
        return ResouresUtils.read("/template/writeable-front.html");
    }

    @Override
    public String getCardBackTemplate() throws IOException {
        return ResouresUtils.read("/template/writeable-back.html");
    }
}