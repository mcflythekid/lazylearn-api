package com.lazylearn.api.controller;

import org.apache.tika.Tika;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author McFly the Kid
 */
@RestController
public class FileController extends BaseController {

    @Value("${upload.path}")
    private String uploadPath;

    @RequestMapping("/file/**")
    public void getFile(HttpServletResponse response, HttpServletRequest request) throws Exception{
        String filePath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        filePath = filePath.substring("file".length() + 1);
        String fullPath = uploadPath + filePath;
        File file = new File(fullPath);
        InputStream inputStream = new FileInputStream(file);
        response.setContentType(new Tika().detect(fullPath));
        IOUtils.copy(inputStream, response.getOutputStream());
        inputStream.close();
    }
}