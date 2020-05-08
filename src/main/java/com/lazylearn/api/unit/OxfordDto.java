package com.lazylearn.api.unit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String audio64;

    @JsonIgnore
    private String audioUrl;
}
