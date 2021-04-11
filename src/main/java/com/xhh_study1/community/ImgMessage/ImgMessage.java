package com.xhh_study1.community.ImgMessage;

import lombok.Data;

import java.util.Map;

@Data
public class ImgMessage {

    private String status;
    private Map<String,Object> data;

    public ImgMessage(String status, Map<String, Object> data) {
        this.status = status;
        this.data = data;
    }


}
