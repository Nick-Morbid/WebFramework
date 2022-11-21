package com.system.formwork.controller;

import com.system.formwork.entity.constant.impl.Role;
import com.system.formwork.entity.dto.RegisterDto;
import com.system.formwork.entity.vo.UserVo;
import com.system.formwork.security.jwt.JwtSecurityHandler;
import com.system.formwork.service.UserService;
import com.system.formwork.util.EnumUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Resource(name = "UserService")
    private UserService userService;
    @Resource
    private EnumUtil enumUtil;
    @Resource
    private JwtSecurityHandler jwtSecurityHandler;

    @PostMapping(value = "/login")
    public UserVo login(@RequestBody RegisterDto data, HttpServletResponse response){
        /*登录验证*/
        UserVo userVo = userService.login(data);
        /*准备生成token*/
        String userId = userVo.getUserId();
        String userName = userVo.getUserName();
        Role role = enumUtil.getEnumByDescription(Role.class, userVo.getRoleName());
        String token = jwtSecurityHandler.getToken(userId, userName, role);
        response.setHeader("Authorization",token);
        /*响应结果*/
        return userVo;
    }

    @PostMapping(value = "/register")
    public UserVo register(@RequestBody RegisterDto data){
        return userService.register(data);
    }

}
