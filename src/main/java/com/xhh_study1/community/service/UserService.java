package com.xhh_study1.community.service;

import com.xhh_study1.community.mapper.UserMapper;
import com.xhh_study1.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser==null){
            user.setGmtCreat(System.currentTimeMillis());//创建时间
            user.setGmtModified(user.getGmtCreat());
            userMapper.insert(user);
        }else{
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            dbUser.setBio(user.getBio());
            userMapper.update(dbUser);
        }

    }
}
