package com.breeze.aop;

/**
 * 类过滤器
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
