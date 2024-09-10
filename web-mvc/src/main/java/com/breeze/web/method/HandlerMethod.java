package com.breeze.web.method;

import java.lang.reflect.Method;

public class HandlerMethod {

    private final Object bean;

    private final Method bridgedMethod;

    protected Method getBridgedMethod() {
        return this.bridgedMethod;
    }

    public Object getBean() {
        return bean;
    }

    HandlerMethod(HandlerMethod handlerMethod) {
        this.bridgedMethod = handlerMethod.bridgedMethod;
        this.bean = handlerMethod.bean;
    }
}
