package com.system.formwork.exception.impl;

import com.system.formwork.constant.impl.ResultCode;
import com.system.formwork.exception.CommonException;

/**
 * 身份鉴权类的错误
 *
 * */
public class AuthException extends RuntimeException implements CommonException {
    private String msg;
    private Integer code;


    public AuthException() {
    }

    public AuthException(String message) {
        super(message);
        this.msg = message;
    }

    public AuthException(ResultCode state){
        this.msg = state.getMsg();
        this.code = state.getCode();
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
