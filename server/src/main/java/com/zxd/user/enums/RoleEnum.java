package com.zxd.user.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    BUYER(1,"买家"),
    SELLER(2,"卖家"),
    ;
    private int code;
    private String msg;

    RoleEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
