package com.breeze.beans.factory.support;

import com.breeze.beans.factory.config.BeanDefinition;

/**
 * 具有自动装配能力的Bean工厂
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws Exception {
        Object bean;
        try {
            bean = beanDefinition.getBean().getClass().newInstance();
        } catch (Exception e) {
            throw new Exception("create bean failed");
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
