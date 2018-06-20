package com.mcflythekid.lazylearncore.test;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Vocab;

/**
 * @author McFly the Kid
 */
public interface VocabGenerator {
    Card generate(Vocab vocab);
}
