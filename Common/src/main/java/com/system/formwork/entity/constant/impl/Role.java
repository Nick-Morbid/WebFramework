package com.system.formwork.entity.constant.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.system.formwork.entity.constant.CommonEnum;

public enum  Role implements CommonEnum {
    ADMIN("管理员",0),
    NORMAL("普通用户",1)
    ;

    private final String msg;
    @EnumValue
    private final Integer code;

    Role(String msg, Integer code){
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
