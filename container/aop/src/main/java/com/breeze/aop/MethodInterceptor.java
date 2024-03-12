package com.breeze.aop;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public interface MethodInterceptor extends Callback {

    Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable;
}
