package com.lazylearn.api.unit;

import lombok.*;

/**
 * @author 4nha
 * Date: 2020-04-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OxfordDto {
    private String word;
    private String phonetic;
    private String phrase;
    private String audio;
    private String audio64;
}
