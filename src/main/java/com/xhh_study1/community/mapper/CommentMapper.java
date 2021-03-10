package com.xhh_study1.community.mapper;

import com.xhh_study1.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {

    @Insert("Insert into comment(parent_id,commentator,type,gmt_create,gmt_modified,like_count,content) values(#{parentId},#{commentator},#{type},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
    void insert(Comment comment);

    @Select("select * from comment where parent_id=#{parentId}")
    Comment selectByPrimaryKey(Long parentId);
}
