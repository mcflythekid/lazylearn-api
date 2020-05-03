package com.lazylearn.api.outdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 4nha
 * Date: 2020-05-03
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RandomTopicDto {
    private String name;
    private String slug;
}
