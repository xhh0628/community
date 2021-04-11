package com.xhh_study1.community.dto;

import lombok.Data;

import java.util.Map;

/**
 * Created by codedrinker on 2019/6/26.
 */
@Data
public class FileDTO {
    private int success;
    private String message;
    private String url;

    public FileDTO(int success,String message, String url) {
        this.success = success;
        this.message = message;
        this.url = url;
    }
}
