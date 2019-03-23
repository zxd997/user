package com.zxd.user.dao;
import com.zxd.user.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserInfo,String> {
    UserInfo findByOpenid(String openid);
}
