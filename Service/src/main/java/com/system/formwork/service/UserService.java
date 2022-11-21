package com.system.formwork.service;

import com.system.formwork.entity.dto.RegisterDto;
import com.system.formwork.entity.vo.UserVo;

public interface UserService {
    /**
     * 登录接口
     * */
    UserVo login(RegisterDto data);
    /**
     * 注册接口
     * */
    UserVo register(RegisterDto data);

}
