package com.xhh_study1.community.dto;

import lombok.Data;

@Data
public class CommentCreatDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
