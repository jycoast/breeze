package com.breeze.beans.factory.config;

/**
 * Bean后置处理器
 */
public interface BeanPostProcessor {

    /**
     * 初始化前回调
     *
     * @param bean
     * @param beanName
     * @return
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    /**
     * 初始化后
     *
     * @param bean
     * @param beanName
     * @return
     */
    default Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}
