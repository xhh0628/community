package com.xhh_study1.community.model;

import lombok.Data;

@Data
public class Notification {
    private Long id;
    private Long notifier;
    private Long receiver;
    private Long outerid;
    private Integer type;
    private Long gmtCreate;
    private Integer status;

}
