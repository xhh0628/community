package com.xhh_study1.community.mapper;

import com.xhh_study1.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("Insert into user(name,account_id,token,gmt_Create,gmt_Modified,bio,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreat},#{gmtModified},#{bio},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id")Long id);

    @Select("select * from user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId")String accountId);

    @Update("update user set gmt_Modified=#{gmtModified},name=#{name},token=#{token},avatar_url=#{avatarUrl},bio=#{bio} where account_id=#{accountId}")
    void update(User dbUser);
}
