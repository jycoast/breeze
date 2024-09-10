package com.breeze.web.method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;

/**
 * 可执行的方法处理
 */
public class InvocableHandlerMethod extends HandlerMethod {

    public InvocableHandlerMethod(HandlerMethod handlerMethod) {
        super(handlerMethod);
    }

    public Object invokeForRequest(ServletWebRequest webRequest, ModelAndViewContainer mavContainer, Object[] providedArgs) {
        Method method = getBridgedMethod();
        Object bean = getBean();
        try {
            return method.invoke(bean, method);
        } catch (Exception ex) {
            throw new IllegalStateException("Invocation failure");
        }
    }
}
