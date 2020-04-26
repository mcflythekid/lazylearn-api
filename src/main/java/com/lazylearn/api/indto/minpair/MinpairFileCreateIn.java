package com.lazylearn.api.indto.minpair;

import com.lazylearn.api.indto.EncodedFile;
import lombok.Data;

/**
 * @author 4nha
 * Date: 2020-04-26
 */
@Data
public class MinpairFileCreateIn {
    private EncodedFile encodedFile;
    private String name;
    private String minpairId;
}
