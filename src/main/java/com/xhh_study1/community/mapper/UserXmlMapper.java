package com.xhh_study1.community.mapper;

import com.xhh_study1.community.model.User;
import com.xhh_study1.community.model.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface UserXmlMapper {

    User selectByPrimaryKey(Long id);

    List<User> selectByExample(UserExample example);

}
