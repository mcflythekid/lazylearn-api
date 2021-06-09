package com.lazylearn.api.util;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * @author McFly the Kid
 */
public final class ResouresUtils {

    private ResouresUtils() {
    }

    public static String read(String resourceName) throws IOException {
        return IOUtils.toString(new ClassPathResource(resourceName).getInputStream(), Charsets.UTF_8);
    }

    public static List<String> readLineByLine_old(String resourceName) throws IOException {
        URL url = Resources.getResource(resourceName);
        List<String> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), Charsets.UTF_8));
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            data.add(currentLine);
        }
        return data;
    }

    public static List<String> readLineByLine(String resourceName) throws IOException {
        List<String> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(ResouresUtils.class.getResourceAsStream(resourceName), Charsets.UTF_8));
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            data.add(currentLine);
        }
        return data;
    }

    public static String readResourcesToString(String path) throws IOException {
        return IOUtils.toString(ResouresUtils.class.getResourceAsStream(path), org.apache.commons.io.Charsets.UTF_8);
    }

}
