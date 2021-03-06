package com.lazylearn.api.service;

import com.lazylearn.api.config.env.WiredEnv;
import com.lazylearn.api.indto.EncodedFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private WiredEnv env;

    public void upload(String filePath, String base64String) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(base64String);
        String fullPath = env.getFileUpload() + filePath;
        File file = new File(fullPath);
        file.getParentFile().mkdirs();
        Path destinationFile = Paths.get(fullPath);
        Files.write(destinationFile, bytes);
    }

    public void uploadEncodedFile(String filePath, String encodedFile) throws Exception {
        upload(filePath, encodedFile);
    }

    public void delete(String filePath) throws Exception {
        Files.deleteIfExists(Paths.get(env.getFileUpload() + filePath));
    }

    public void rmDirIfExists(String dirPath) throws IOException {
        FileUtils.deleteDirectory(new File(env.getFileUpload() + dirPath));
    }

    public EncodedFile readFileFromPath(String path) throws IOException {
        String ext = FilenameUtils.getExtension(path);

        byte[] fileContent = FileUtils.readFileToByteArray(new File(env.getFileUpload() + path));
        String content = Base64.getEncoder().encodeToString(fileContent);

        return EncodedFile.builder().ext(ext).content(content).build();
    }
}