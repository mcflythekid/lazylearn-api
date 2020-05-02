package com.lazylearn.api.clone.dto;

/**
 * @author 4nha
 * Date: 2020-05-02
 */

import com.lazylearn.api.indto.card.CardCreateIn;
import com.lazylearn.api.indto.deck.DeckCreateIn;
import com.lazylearn.api.indto.minpair.MinpairCreateIn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MinpairCloneDto {
    private MinpairCreateIn minpair;
}
