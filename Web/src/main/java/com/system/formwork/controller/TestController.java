package com.system.formwork.controller;

import com.system.formwork.entity.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/test01")
    public User test01(){
        return new User().setId("1").setName("nick");
    }
}
