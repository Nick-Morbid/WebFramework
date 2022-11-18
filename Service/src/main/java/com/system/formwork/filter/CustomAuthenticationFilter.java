package com.system.formwork.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.formwork.entity.bo.UserBo;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/*
* springsecurity默认采用key/value的表单形式进行登录验证，若要采用json格式，则需要重写过滤器
* */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //attempt Authentication when Content-Type is json
        if(MediaType.APPLICATION_JSON_UTF8_VALUE.equals(request.getContentType())
                ||MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())){
            //use jackson to deserialize json
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = request.getInputStream()){
                UserBo myUser = mapper.readValue(is,UserBo.class);
                authRequest = new UsernamePasswordAuthenticationToken(
                        myUser.getUsername(), myUser.getPassword());
            }catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            }finally {
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        //transmit it to UsernamePasswordAuthenticationFilter
        else {
            return super.attemptAuthentication(request, response);
        }
    }
}
