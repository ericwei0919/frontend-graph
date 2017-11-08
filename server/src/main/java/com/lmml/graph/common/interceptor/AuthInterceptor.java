package com.lmml.graph.common.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        final String path = request.getServletPath();
        if (path.endsWith("user/login")) {
            return true;
        } else {
            final String authHeader = request.getHeader("Authorization");
            final String token = authHeader.substring("Bearer".length() + 1);
            Claims claims = Jwts.parser().setSigningKey("air_secretKey").parseClaimsJws(token).getBody();
            if (claims == null) {
                return false;
            }
            authService.setClaims(claims);
            if (authService.getUserId() == null) {
                return false;
            }
            if (authService.getUserId().longValue() < 1000) {

                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }


}
