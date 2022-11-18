package com.system.formwork.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.formwork.constant.impl.ResultCode;
import com.system.formwork.constant.impl.Role;
import com.system.formwork.entity.bo.UserBo;
import com.system.formwork.entity.dto.RegisterDto;
import com.system.formwork.entity.pojo.User;
import com.system.formwork.entity.vo.UserVo;
import com.system.formwork.exception.impl.AuthException;
import com.system.formwork.exception.impl.ServiceException;
import com.system.formwork.mapper.UserMapper;
import com.system.formwork.mapstruct.UserConvertor;
import com.system.formwork.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service(value = "UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserConvertor userConvertor;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserBo userBo = new UserBo();
        User user = userMapper.selectById(s);
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

    @Override
    public UserVo register(RegisterDto data) {
        /*是否重复注册*/
        User user = userMapper.selectById(data.getId());
        if (user!=null) throw new ServiceException(ResultCode.NAME_ALREADY_EXIST);
        /*生成pojo对象*/
        User user1 = new User();
        user1.setPassword(passwordEncoder.encode(data.getPassword()));
        user1.setName(data.getName());
        user1.setRole(Role.NORMAL);
        user1.setId(data.getId());
        /*插入数据*/
        userMapper.insertUser(user1);
        return userConvertor.UserToUserVo(user1);
    }
}




