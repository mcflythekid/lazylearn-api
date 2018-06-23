package com.mcflythekid.lazylearncore.util;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;

/**
 * @author McFly the Kid
 */
public final class ResouresUtils {

    private ResouresUtils(){}

    public static String read(String resourceName) throws IOException {
        URL url = Resources.getResource(resourceName);
        return Resources.toString(url, Charsets.UTF_8);
    }
}
