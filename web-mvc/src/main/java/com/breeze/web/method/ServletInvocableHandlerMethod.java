package com.breeze.web.method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ServletInvocableHandlerMethod extends InvocableHandlerMethod {

    private static final Logger logger = LoggerFactory.getLogger(ServletInvocableHandlerMethod.class);

    ServletInvocableHandlerMethod(HandlerMethod handlerMethod) {
        super(handlerMethod);
    }

    public void invokeAndHandle(ServletWebRequest webRequest, ModelAndViewContainer mavContainer, Object... providedArgs) {
        Object returnValue = invokeForRequest(webRequest, mavContainer, providedArgs);
        logger.info("method invoke value:{}", returnValue);
    }
}
