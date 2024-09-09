package com.breeze.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerInterceptor {

    default boolean preHandle(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

    default boolean postHandle(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }
}
