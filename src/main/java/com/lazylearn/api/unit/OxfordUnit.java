package com.lazylearn.api.unit;

import org.apache.tomcat.util.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 4nha
 * Date: 2020-04-19
 */
public class OxfordUnit {
    public OxfordDto craw(String word) throws Exception {
        Document document = Jsoup.connect("https://www.oxfordlearnersdictionaries.com/definition/american_english/" + word).timeout(1000).get();
        Element firstPhonetic = document.select(".phon").first();
        if (firstPhonetic == null){
            throw new Exception("Phonetic not found: " + word);
        }

        Element sound = (Element) firstPhonetic.siblingNodes().get(0);
        if (sound == null){
            throw new Exception("Audio not found: " + word);
        }

        firstPhonetic.selectFirst("span.name").remove();
        firstPhonetic.select("span.wrap").remove();

        Element phrase = document.selectFirst("span.x");

        String audio = sound.attr("data-src-mp3");
        String audio64 = getBase64EncodedImage(audio);


        return OxfordDto.builder().word(word).phonetic(firstPhonetic.text())
                .audio(audio).phrase(phrase.text()).audio64(audio64).build();
    }

    static String getBase64EncodedImage(String imageURL) throws IOException {
        java.net.URL url = new java.net.URL(imageURL);
        InputStream is = url.openStream();
        byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(is);
        return Base64.encodeBase64String(bytes);
    }


    public static void test(String[] args) throws Exception {
        OxfordDto oxfordDto = new OxfordUnit().craw("kiss");
        System.out.println(oxfordDto);
    }
}
