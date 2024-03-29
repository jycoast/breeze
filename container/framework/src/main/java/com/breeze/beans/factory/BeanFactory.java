package com.breeze.beans.factory;

import com.breeze.beans.factory.config.BeanDefinition;

/**
 * Bean工厂
 */
public interface BeanFactory {

    Object getBean(String beanName) throws Exception;

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
