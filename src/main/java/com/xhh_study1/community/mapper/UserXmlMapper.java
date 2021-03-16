package com.xhh_study1.community.mapper;

import com.xhh_study1.community.model.User;
import com.xhh_study1.community.model.UserExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserXmlMapper {
    List<User> selectByExample(UserExample example);
}
