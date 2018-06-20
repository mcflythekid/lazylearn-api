package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.indto.EncodedFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author McFly the Kid
 */
@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    public void upload(String filePath, String base64String) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(base64String);
        String fullPath = uploadPath + filePath;
        File file = new File(fullPath);
        file.getParentFile().mkdirs();
        Path destinationFile = Paths.get(fullPath);
        Files.write(destinationFile, bytes);
    }

    public void uploadEncodedFile(String filePathWithoutExt, EncodedFile encodedFile) throws Exception {
        upload(filePathWithoutExt + "." + encodedFile.getExt(), encodedFile.getContent());
    }

    public void delete(String filePath) throws Exception{
        Files.deleteIfExists(Paths.get(uploadPath + filePath));
    }
}