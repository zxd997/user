package com.zxd.user.controller;

import com.zxd.user.constant.CookieConstant;
import com.zxd.user.constant.RedisConstant;
import com.zxd.user.enums.ResultEnum;
import com.zxd.user.enums.RoleEnum;
import com.zxd.user.model.UserInfo;
import com.zxd.user.service.UserService;
import com.zxd.user.utils.CookieUtil;
import com.zxd.user.utils.ResultVOUtil;
import com.zxd.user.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response){
        //1.匹配openid
        UserInfo userInfo = userService.findUserInfoByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2.判断角色
        if (userInfo.getRole() != RoleEnum.BUYER.getCode()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        //3.设置cookie
        CookieUtil.setCookie(response, CookieConstant.OPENID,openid,CookieConstant.expire);

        return ResultVOUtil.success();
    }
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletResponse response, HttpServletRequest request){
        //判断是否已登陆
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        if (cookie !=null && !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue())))){
            log.info("this is ={}","判断为已经登陆");
            return ResultVOUtil.success();
        }
        //1.匹配openid
        UserInfo userInfo = userService.findUserInfoByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2.判断角色
        if (userInfo.getRole() != RoleEnum.SELLER.getCode()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        //3.设置redis k uuid v xyz
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),openid,expire, TimeUnit.SECONDS);
        //4.设置cookie
        CookieUtil.setCookie(response, CookieConstant.TOKEN,token,CookieConstant.expire);
        log.info("this is ={}","没登过 设置了 redis 并 cookie");
        return ResultVOUtil.success();
    }
    @GetMapping("/hh")
    public String get1(){
        return "success";
    }
}
