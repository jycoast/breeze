package com.breeze.beans.factory.config;

/**
 * 单例Bean注册中心
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
