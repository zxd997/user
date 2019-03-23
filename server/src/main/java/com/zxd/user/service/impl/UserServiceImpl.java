package com.zxd.user.service.impl;

import com.zxd.user.dao.UserDao;
import com.zxd.user.model.UserInfo;
import com.zxd.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserInfo findUserInfoByOpenid(String openid) {
        return userDao.findByOpenid(openid);
    }
}
