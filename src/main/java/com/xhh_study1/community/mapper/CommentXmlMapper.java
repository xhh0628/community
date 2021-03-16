package com.xhh_study1.community.mapper;

import com.xhh_study1.community.model.Comment;
import com.xhh_study1.community.model.CommentExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentXmlMapper {
    List<Comment> selectByExample(CommentExample example);
}
