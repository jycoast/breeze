package com.breeze.beans.factory.config;

/**
 * Bean 实例化 回调
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 实例化前
     *
     * @param beanClass
     * @param beanName
     * @return
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName);

    /**
     * 实例化后回调
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInstantiation(Object bean, String beanName);
}
