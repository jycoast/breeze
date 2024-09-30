package com.breeze.beans.factory.config;

import com.breeze.beans.factory.BeanFactory;
import com.breeze.beans.factory.support.ListableBeanFactory;
import com.breeze.context.annotation.ApplicationContextAwareProcessor;

/**
 * 支持配置和可以遍历的BeanFactory
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory {

    /**
     * 初始化Bean
     */
    void preInitializationSingletons();


    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
