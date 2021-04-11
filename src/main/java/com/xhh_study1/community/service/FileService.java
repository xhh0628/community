package com.xhh_study1.community.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.xhh_study1.community.ImgMessage.ImgMessage;
import com.xhh_study1.community.dto.FileDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class FileService {

    private static String accessKeyId ="LTAI5tAaeLjNVqsxLPL5YSTS";
    private static String accessKeySecret  ="MnabLYQ1tshs69Ros7Goo7J6wTH4kR";
    private static String endpoint  ="oss-cn-beijing.aliyuncs.com";

    //oss上传文件
    public FileDTO upload(MultipartFile uploadFile) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 返回的文件访问路径
        String url = "";
        try {

            //获取文件的原始名字
            String originalfileName = uploadFile.getOriginalFilename();
            // 按日期存储
            Date today = new Date();
            SimpleDateFormat simpDate = new SimpleDateFormat("yyyy_MM_dd");
            String fileTimeAddress = simpDate.format(today);
            //重新命名文件
            String suffix = originalfileName.substring(originalfileName.lastIndexOf(".") + 1);
            String fileName = new Date().getTime() + "-img." + suffix;

            // 获得文件流
            InputStream inputStream = uploadFile.getInputStream();

            // 上传到OSS
            ossClient.putObject("xhh-al-bucket1", "test/"+fileTimeAddress+"/" + fileName, inputStream);

            url += "http://xhh-al-bucket1.oss-cn-beijing.aliyuncs.com/test/"+fileTimeAddress+"/" + fileName;
            System.out.println("下载url是:" + url);


        } catch (IOException e) {
            e.printStackTrace();
        }

        // 是否有可访问的地址
        if (url.length() < 2) {
            return new FileDTO(0,"fail", null);
        }
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("image_src", url);
        return new FileDTO(1,"success", url);

    }
}
