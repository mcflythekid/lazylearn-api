package com.lazylearn.api.unit;

import com.lazylearn.api.config.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 4nha
 * Date: 2020-04-19
 */
@Slf4j
public class OxfordUnit {

    public OxfordDto craw(String word) throws Exception{
        return crawx(word);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(crawx("lion"));
    }

    public static OxfordDto crawx(String rawWords) throws Exception {
        String words = rawWords.replaceAll("\\s{2,}", " ").trim();
        String[] array = words.split("\\s");
        if (array.length == 1){
            return crawlSingle(array[0]);
        }
        if (array.length > 2){
            throw new AppException(404, "Not allowed 3 words");
        }

        List<OxfordDto> oxfordDtoList = new ArrayList<>();
        for (String word : array){
            oxfordDtoList.add(crawlSingle(word));
        }
        String word = "";
        String phonetic = "";
        List<InputStream> streams = new ArrayList<>();
        for (OxfordDto oxfordDto : oxfordDtoList){
            word += oxfordDto.getWord().trim() + " ";
            phonetic += oxfordDto.getPhonetic().replaceAll("\\/", "").trim() + " ";
            streams.add(new URL(oxfordDto.getAudioUrl()).openStream());
        }
        word = word.trim();
        phonetic = "/" + phonetic.trim() + "/";

        SequenceInputStream sistream = new SequenceInputStream(streams.get(0), streams.get(1));

        byte[] audioBytes = new byte[sistream.available()];
        sistream.read(audioBytes, 0, audioBytes.length);
        sistream.close();
        String audio64 = Base64.encodeBase64String(audioBytes);


        return OxfordDto.builder()
                .word(word)
                .phonetic(phonetic)
                .audio64(audio64)
                .build();
    }

    public static OxfordDto crawlSingle(String rawWord) throws Exception {
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

            return OxfordDto.builder().word(word).phonetic(phonetic)
                    .audioUrl(audioUrl).phrase(phraseString).audio64(audio64).build();
        } catch (org.jsoup.HttpStatusException e){
            if (e.getStatusCode() == 404){
                throw new AppException(e.getStatusCode());
            }
            throw e;
        } catch (Exception e){
            throw e;//debug
        }
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
