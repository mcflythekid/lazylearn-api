package com.lazylearn.api.test;

import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Vocab;

/**
 * @author McFly the Kid
 */
public interface VocabGenerator {
    Card generate(Vocab vocab);
}
