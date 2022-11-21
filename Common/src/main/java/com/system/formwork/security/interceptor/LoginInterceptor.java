package com.system.formwork.security.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.system.formwork.entity.constant.impl.ResultCode;
import com.system.formwork.entity.constant.impl.Role;
import com.system.formwork.entity.exception.impl.AuthException;
import com.system.formwork.entity.exception.impl.ServiceException;
import com.system.formwork.security.context.Authorization;
import com.system.formwork.security.context.SecurityContext;
import com.system.formwork.security.context.SecurityContextHolder;
import com.system.formwork.security.jwt.JwtSecurityHandler;
import com.system.formwork.util.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Author:陈宏侨
 * Edu:Fuzhou University
 * Date:2021/10/18
 * Project:Okr Leader
 * */
//登录认证拦截器
@Component(value = "LoginInterceptor")
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private JwtSecurityHandler jwtSecurityHandler;
    @Resource
    private EnumUtil enumUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        /*获取token*/
        String token = request.getHeader("Authorization");
        log.info("ThreadId:{}",Thread.currentThread().getId());
        log.info("SessionId:{}",request.getSession().getId());
        log.info("Authorization:{}",token);
        /*token鉴权*/
        if (token==null) {
            throw new AuthException(ResultCode.NOT_ACCESS);
        }
        try {
            /*将鉴权消息存入安全上下文容器*/
            DecodedJWT verify = jwtSecurityHandler.verify(token);
            String id = verify.getClaim("id").asString();
            String name = verify.getClaim("name").asString();
            Role role = enumUtil.getEnumByCode(Role.class, verify.getClaim("role").asInt());
            Authorization authorization = new Authorization()
                    .addInfo("id",id)
                    .addInfo("name",name)
                    .addInfo("role",role);
            SecurityContextHolder.setContext(new SecurityContext(authorization));
            log.info("用户:{}(ID为:{},角色为:{})发起请求",name,id,role.getMsg());
            return true;
        }catch (SignatureVerificationException e){//无效签名
            throw new ServiceException(ResultCode.SIGNATURE_NOT_MATCH);
        }catch (TokenExpiredException e){//token过期
            throw new ServiceException(ResultCode.TOKEN_OVERDUE);
        }catch (AlgorithmMismatchException e){//token算法出错
            throw new ServiceException(ResultCode.ALGORITHM_ERROR);
        }catch (Exception e){//token无效
            throw new ServiceException(ResultCode.TOKEN_VOID);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        /*将鉴权消息从安全上下文中移除*/
        SecurityContextHolder.clearContext();
    }
}
