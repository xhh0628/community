package com.xhh_study1.community.mapper;

import com.xhh_study1.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("Insert into user(name,account_id,token,gmt_Create,gmt_Modified,bio,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreat},#{gmtModified},#{bio},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(Integer id);
}
