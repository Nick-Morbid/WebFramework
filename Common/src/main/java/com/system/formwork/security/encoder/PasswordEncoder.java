package com.system.formwork.security.encoder;

/**
 * 密码加密器
 * */
public interface PasswordEncoder {
    /**
     * 对password进行加密
     * */
    String encode(String password);

    /**
     * 检验verity是否为encode加密前的值
     * */
    boolean verity(String verity,String encode);
}
