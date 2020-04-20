package com.example.interceptor;

import com.example.annotation.Authorize;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pengsong
 * @date 2020/4/16 10:16 上午
 */
public class AuthorizeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        boolean b = method.hasMethodAnnotation(Authorize.class);
        Authorize annotation = method.getBeanType().getAnnotation(Authorize.class);
        if (b || annotation != null) {
            System.out.println("被authorize注解的方法或类,需要验证信息！");
            return false;
        }
        System.out.println("无需验证信息------");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
