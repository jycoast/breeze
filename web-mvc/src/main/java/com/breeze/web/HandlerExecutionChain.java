package com.breeze.web;

import com.breeze.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;

public class HandlerExecutionChain {

    private List<HandlerInterceptor> interceptorList = new ArrayList<>();

    public Object getHandler() {
        return handler;
    }

    // 方法处理器
    private Object handler;
}
