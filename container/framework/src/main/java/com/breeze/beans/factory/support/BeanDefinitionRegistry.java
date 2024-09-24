package com.breeze.beans.factory.support;

import com.breeze.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition 注册中心
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
