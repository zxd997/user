package com.zxd.user.service;

import com.zxd.user.model.UserInfo;

public interface UserService {
    UserInfo findUserInfoByOpenid(String openid);
}
