package com.breeze.beans.factory.config;

/**
 * Bean 实例化 回调
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName);

    Object postProcessAfterInstantiation(Object bean, String beanName);
}
