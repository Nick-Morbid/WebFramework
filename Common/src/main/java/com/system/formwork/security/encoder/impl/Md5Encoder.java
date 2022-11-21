package com.system.formwork.security.encoder.impl;

import com.system.formwork.entity.constant.impl.ResultCode;
import com.system.formwork.entity.exception.impl.ServiceException;
import com.system.formwork.security.encoder.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Slf4j
@Component(value = "PasswordEncoder")
public class Md5Encoder implements PasswordEncoder {
    @Override
    public String encode(String password) {
        String md5Str;
        try {
            //创建MD5算法消息摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //生成的哈希值的字节数组
            byte[] md5Bytes = md.digest(password.getBytes());
            md5Str = bytes2Hex(md5Bytes);
        }catch(Exception e) {
            log.error("密码加密错误，原因是：{}",e.getCause().toString());
            throw new ServiceException(ResultCode.SERVER_INNER_ERROR);
        }
        return md5Str;
    }

    /**
     * 2进制转16进制的方法
     * */
    private static String bytes2Hex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        int temp;
        for (byte aByte : bytes) {
            temp = aByte;
            if (temp < 0) {
                temp += 256;
            }
            if (temp < 16) {
                result.append("0");
            }
            result.append(Integer.toHexString(temp));
        }
        return result.toString();
    }

    @Override
    public boolean verity(String verity, String encode) {
        return encode.equals(encode(verity));
    }
}
