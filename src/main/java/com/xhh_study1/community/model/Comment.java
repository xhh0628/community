package com.xhh_study1.community.model;

import lombok.Data;

@Data
public class Comment {

    private Long parentId;

    private Long commentator;

    private Integer type;

    private Long gmtCreate;

    private Long gmtModified;

    private Long likeCount;

    private String content;
}
