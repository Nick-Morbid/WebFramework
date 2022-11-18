package com.system.formwork.controller;

import com.system.formwork.entity.dto.RegisterDto;
import com.system.formwork.entity.vo.UserVo;
import com.system.formwork.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Resource(name = "UserService")
    private UserService userService;

    @PostMapping(value = "/register")
    public UserVo register(@RequestBody RegisterDto data){
        return userService.register(data);
    }


}
