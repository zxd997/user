package com.zxd.user.controller;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

public class UserControllerTest {
    @Test
    public void fun1(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String s = jedis.get("token_eaaf3780-c54a-4dc6-8c59-aaf8a93c6875");
        System.out.println(s);
    }
}