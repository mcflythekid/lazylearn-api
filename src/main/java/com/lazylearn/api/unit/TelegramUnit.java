package com.lazylearn.api.unit;

import com.lazylearn.api.config.env.WiredEnv;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author 4nha
 * Date: 2020-05-01
 */
@Component
@Slf4j
public class TelegramUnit {

    @Autowired
    private WiredEnv wiredEnv;

    // OLD
    public static final int TIMEOUT_MILLIS = 10000;
    public static final String HOOK = "https://api.telegram.org/bot1244850034:AAEwBmgDNQ0gm2P8Kyk4cedWflb4holSgzY/sendMessage?chat_id=-425730701&text=";

    // NEW
    public static final String URL_FORMAT = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
    public static final String TOKEN = "1244850034:AAEwBmgDNQ0gm2P8Kyk4cedWflb4holSgzY";
    public static final int ID = -425730701;

    private static void send(String text) throws IOException {
        Jsoup.connect(HOOK + encode(text)).timeout(TIMEOUT_MILLIS).post();
    }

    private static String encode(String text) throws UnsupportedEncodingException {
        return URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
    }

    private static void sendToTelegram(String text) throws IOException {
        final String urlString = String.format(URL_FORMAT, TOKEN, ID, encode(text));
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        InputStream is = new BufferedInputStream(conn.getInputStream());
    }

    public void sendAsync(String message, String invoker) {
        if (invoker.equalsIgnoreCase("Administrator")) {
            log.info("Telegram disabled for 'Administrator', stop sending message");
            return;
        }

        if (invoker.equalsIgnoreCase("odopoc@gmail.com")) {
            log.info("Telegram disabled for 'odopoc@gmail.com', stop sending message");
            return;
        }

        sendAsync(message);
    }

    public void sendAsync(String message) {
        if (!wiredEnv.isTelegramEnabled()) {
            log.info("Telegram disabled, stop sending message");
            return;
        }

        new Thread(() -> {
            try {
                sendToTelegram(message);
            } catch (IOException e) {
                log.error("Cannot send message: [" + message + "]", e);
            }
        }, "telegram-sender-" + UUID.randomUUID().toString()).start();
    }
}
