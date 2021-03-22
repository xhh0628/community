package com.xhh_study1.community.mapper;

import com.xhh_study1.community.model.Comment;
import com.xhh_study1.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CommentMapper {

    @Insert("Insert into comment(parent_id,commentator,type,gmt_create,gmt_modified,like_count,content,comment_count) values(#{parentId},#{commentator},#{type},#{gmtCreate},#{gmtModified},#{likeCount},#{content},#{commentCount})")
    void insert(Comment comment);

    @Select("select * from comment where id =#{parentId}")
    Comment selectByPrimaryKey(Long parentId);

    @Update("update comment set comment_count=comment_count+#{commentCount} where id=#{id}")
    void updateCommentCount(Comment comment);

}
