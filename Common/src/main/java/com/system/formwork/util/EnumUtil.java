package com.system.formwork.util;

import com.system.formwork.entity.constant.CommonEnum;
import org.springframework.stereotype.Component;

/*
* 枚举工具类，可以通过枚举类中的属性来获取对应的枚举类实例！
* */
@Component
@SuppressWarnings("all")
public class EnumUtil {
    /*
    * 根据编码获取枚举实例
    * */
    public <T extends CommonEnum> T getEnumByCode(Class<T> clazz, Integer code){
        for (T enumConstant : clazz.getEnumConstants()) {
            if (enumConstant.getCode().equals(code)) return enumConstant;
        }
        return null;
    }

    /*
    * 根据描述获取枚举实例
    * */

    public <T extends CommonEnum> T getEnumByDescription(Class<T> clazz,String description){
        for (T enumConstant : clazz.getEnumConstants()) {
            if (enumConstant.getMsg().equals(description)) return enumConstant;
        }
        return null;
    }
}
