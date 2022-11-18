package com.system.formwork.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.formwork.constant.impl.ResultCode;
import com.system.formwork.entity.bo.UserBo;
import com.system.formwork.entity.pojo.User;
import com.system.formwork.exception.impl.AuthException;
import com.system.formwork.service.UserService;
import com.system.formwork.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author LENOVO
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-07-23 14:36:04
*/
@Service(value = "UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserBo userBo = new UserBo();
        User user = getById(s);
        if (user!=null){
            List<GrantedAuthority> list = new ArrayList<>();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+user.getRole().name());
            list.add(grantedAuthority);
            userBo.setUsername(user.getId()).setPassword(user.getPassword()).setAuthorities(list);
        }else {
            throw new AuthException(ResultCode.NOT_FIND_USER);
        }
        return userBo;
    }
}




