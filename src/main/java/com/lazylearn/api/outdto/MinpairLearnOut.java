package com.lazylearn.api.outdto;

import com.lazylearn.api.entity.Minpair;
import com.lazylearn.api.entity.MinpairFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 4nha
 * Date: 2020-05-02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MinpairLearnOut {
    private Minpair minpair;
    private List<MinpairFile> left;
    private List<MinpairFile> right;
}
