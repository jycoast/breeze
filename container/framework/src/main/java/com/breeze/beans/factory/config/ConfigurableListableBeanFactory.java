package com.breeze.beans.factory.config;

import com.breeze.beans.factory.BeanFactory;

/**
 * 支持配置和可以遍历的BeanFactory
 */
public interface ConfigurableListableBeanFactory extends BeanFactory {

    /**
     * 初始化Bean
     */
    void preInitializationSingletons();
}
