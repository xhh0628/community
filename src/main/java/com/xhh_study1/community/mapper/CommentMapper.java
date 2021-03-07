package com.xhh_study1.community.mapper;

import com.xhh_study1.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    @Insert("Insert into comment(parent_id,commentator,type,gmt_create,gmt_modified,like_count,content) values(#{parentId},#{commentator},#{type},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
    void insert(Comment comment);
}
