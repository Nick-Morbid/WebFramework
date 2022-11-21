package com.system.formwork.service.impl;

import com.system.formwork.entity.constant.impl.ResultCode;
import com.system.formwork.entity.constant.impl.Role;
import com.system.formwork.entity.dto.RegisterDto;
import com.system.formwork.entity.exception.impl.ServiceException;
import com.system.formwork.entity.pojo.User;
import com.system.formwork.entity.vo.UserVo;
import com.system.formwork.mapper.UserMapper;
import com.system.formwork.mapstruct.UserConvertor;
import com.system.formwork.security.encoder.PasswordEncoder;
import com.system.formwork.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service(value = "UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserConvertor userConvertor;
    @Resource(name = "PasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public UserVo login(RegisterDto data) {
        /*参数校验*/
        String id = data.getId();
        String password = data.getPassword();
        if (id==null||password==null) throw new ServiceException(ResultCode.EMPTY_PARMA);
        /*密码检测*/
        User user = userMapper.selectById(id);
        if (user==null) throw new ServiceException(ResultCode.NOT_FIND_USER);
        if (!passwordEncoder.verity(password,user.getPassword()))throw new ServiceException(ResultCode.LOGIN_FAILURE);
        /*组装视图对象*/
        return userConvertor.UserToUserVo(user);
    }


    @Override
    public UserVo register(RegisterDto data) {
        /*参数检测*/
        if (data.getId()==null||data.getName()==null||data.getPassword()==null) throw new ServiceException(ResultCode.EMPTY_PARMA);
        /*重复注册检测*/
        User user = userMapper.selectById(data.getId());
        if (user!=null) throw new ServiceException(ResultCode.ID_ALREADY_EXIST);
        /*生成pojo对象*/
        User user1 = new User()
                            .setPassword(passwordEncoder.encode(data.getPassword()))
                            .setName(data.getName())
                            .setRole(Role.NORMAL)
                            .setId(data.getId());
        /*插入数据*/
        userMapper.insertUser(user1);
        /*返回视图对象*/
        return userConvertor.UserToUserVo(user1);
    }


}




