package com.breeze.web.method.annotation;

import com.breeze.web.HandlerExecutionChain;
import com.breeze.web.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

public class RequestMappingHandlerAdapter implements HandlerMapping {

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) {
        return null;
    }
}
