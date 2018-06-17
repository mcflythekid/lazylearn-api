package com.mcflythekid.lazylearncore.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.activation.MimetypesFileTypeMap;
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
        filePath = filePath.substring(5);
        String fullPath = uploadPath + filePath;
        File file = new File(fullPath);
        InputStream inputStream = new FileInputStream(file);
        String contentType = new MimetypesFileTypeMap().getContentType(new File(fullPath));
        response.setContentType(contentType);
        IOUtils.copy(inputStream, response.getOutputStream());
        inputStream.close();
    }
}