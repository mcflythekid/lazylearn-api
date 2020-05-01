package com.lazylearn.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author 4nha
 * Date: 2020-05-01
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtils {

    public static List<String> readLines(String path) throws IOException {
        Charset charset = Charset.forName("UTF-8");
        List<String> result = Files.readAllLines(Paths.get(path), charset);
        return result;
    }
}
