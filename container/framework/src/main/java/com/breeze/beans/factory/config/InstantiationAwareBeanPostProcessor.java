package com.breeze.beans.factory.config;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName);

    Object postProcessAfterInstantiation(Object bean, String beanName);
}
