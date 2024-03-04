package com.breeze.beans.factory.support;

import com.breeze.beans.factory.BeanFactory;
import com.breeze.beans.factory.config.BeanDefinition;

public class AbstractBeanFactory implements BeanFactory {

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {

    }
}
