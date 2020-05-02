package com.lazylearn.api.clone.dto;

/**
 * @author 4nha
 * Date: 2020-05-02
 */

import com.lazylearn.api.indto.vocab.VocabCreateIn;
import com.lazylearn.api.indto.vocabdeck.VocabdeckCreateIn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VocabdeckCloneDto {
    private VocabdeckCreateIn vocabdeck;
    private List<VocabCreateIn> vocabs;
}
