package com.system.formwork.security;

import com.system.formwork.util.JsonUtil;
import com.system.formwork.properties.JwtProperties;
import com.system.formwork.entity.pojo.User;
import com.system.formwork.entity.vo.Result;
import com.system.formwork.entity.vo.UserVo;
import com.system.formwork.service.UserService;
import com.system.formwork.util.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 认证成功处理器，在这里生成token并放入response头中！
* */
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

    }
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private JwtProperties jwtProperties;
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        String userId = authentication.getName();
        User user = userService.getById(userId);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        Result<?> result = Result.success(userVo);

        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JsonUtil.toJson(result));

        String token = jwtTokenUtil.generateToken(userId);
        httpServletResponse.setHeader(jwtProperties.getHeader(),token);
        //将生成的authentication放入容器中，生成安全的上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
