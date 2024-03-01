package com.breeze.beans.factory.support;

import com.breeze.beans.factory.config.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {

    private Object beanClass;

    public GenericBeanDefinition(Object beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public Object getBean() {
        return beanClass;
    }
}
