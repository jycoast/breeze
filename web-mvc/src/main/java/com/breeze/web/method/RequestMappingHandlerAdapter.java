package com.breeze.web.method;

import com.breeze.web.method.annotation.AbstractHandlerMethodAdapter;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestMappingHandlerAdapter extends AbstractHandlerMethodAdapter {

    @Override
    protected ModelAndView handleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {
        ModelAndView mav = invokeHandlerMethod(request, response, handler);
        return mav;
    }

    private ModelAndView invokeHandlerMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {
        ServletInvocableHandlerMethod invocableMethod = new ServletInvocableHandlerMethod(handler);
        invocableMethod.invokeAndHandle(null, null, null);
        return null;
    }
}