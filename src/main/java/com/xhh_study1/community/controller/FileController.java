package com.xhh_study1.community.controller;


import com.xhh_study1.community.ImgMessage.ImgMessage;
import com.xhh_study1.community.dto.FileDTO;
import com.xhh_study1.community.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by codedrinker on 2019/6/26.
 */
@Controller
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

/*    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {

        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/default-avatar.png");
        return fileDTO;
    }*/

    @RequestMapping("/file/alOss")
    @ResponseBody
    public FileDTO alOssTest(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile uploadFile = multipartRequest.getFile("editormd-image-file");
        return fileService.upload(uploadFile);


    }


}
