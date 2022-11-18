package com.system.formwork.service;

import com.system.formwork.entity.dto.RegisterDto;
import com.system.formwork.entity.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.system.formwork.entity.vo.UserVo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    /**
     * 注册接口
     * */
    UserVo register(RegisterDto data);
}
