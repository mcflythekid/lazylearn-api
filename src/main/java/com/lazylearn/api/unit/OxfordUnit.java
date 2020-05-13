package com.lazylearn.api.unit;

import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.indto.EncodedFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 4nha
 * Date: 2020-04-19
 */
@Slf4j
public class OxfordUnit {

    public static void main(String[] args) throws Exception {
       System.out.println(new OxfordUnit().crawlSingle("lion"));
    }

    public VocabSampleDto crawlSingle(String rawWord) throws Exception {
        String word = rawWord.toLowerCase().trim();

        try {
            Connection.Response response = Jsoup.connect("https://www.oxfordlearnersdictionaries.com/definition/english/" + word)
                    .followRedirects(true)
                    .timeout(10000).execute();

            Document document = response.parse();
            String url = response.url().toString();

            // Phonetic & sound
            Element firstMeetElement = document.select(".phons_n_am").first();
            if (firstMeetElement == null) {
                firstMeetElement = document.select(".phons_br").first();
                if (firstMeetElement == null){
                    throw new Exception("Item not found: " + word);
                }
            }
            if (firstMeetElement.childrenSize() < 2){
                throw new Exception("Must have 2 child");
            }
            Element soundElement = firstMeetElement.selectFirst("div.sound");
            Element phoneticElement = soundElement.nextElementSibling();
            String audioUrl = soundElement.attr("data-src-mp3");
            String audio64 = getBase64EncodedImage(audioUrl);
            String phonetic = phoneticElement.text();

            Element phrase = document.selectFirst("span.x");
            String phraseString = phrase != null ? phrase.text() : "";

            return VocabSampleDto.builder().word(word).phonetic(phonetic)
                    .encodedAudioFile(EncodedFile.builder().ext("mp3").content(audio64).build())
                    .audioUrl(audioUrl).phrase(phraseString).audio64(audio64).build();
        } catch (org.jsoup.HttpStatusException e){
            if (e.getStatusCode() == 404){
                throw new AppException(e.getStatusCode());
            }
            throw e;
        } catch (Exception e){
            log.error("Cannot fetch word: " + rawWord, e);
            throw new AppException(404, "Error");
        }
    }

    static String getBase64EncodedImage(String imageURL) throws IOException {
        java.net.URL url = new java.net.URL(imageURL);
        InputStream is = url.openStream();
        byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(is);
        return Base64.encodeBase64String(bytes);
    }
}
