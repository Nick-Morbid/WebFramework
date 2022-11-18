package com.system.formwork.service.impl;

import com.system.formwork.service.TestService;
import org.springframework.stereotype.Service;

@Service(value = "TestService")
public class TestServiceImpl implements TestService {
    @Override
    public String hello() {
        return "Hello";
    }
}
