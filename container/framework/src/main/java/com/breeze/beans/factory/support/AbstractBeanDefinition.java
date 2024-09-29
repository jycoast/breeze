package com.breeze.beans.factory.support;

import com.breeze.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanDefinition implements BeanDefinition {

    private volatile Object beanClass;

    public AbstractBeanDefinition(BeanDefinition original) {
        if (original instanceof AbstractBeanDefinition) {
            AbstractBeanDefinition originalDefinition = (AbstractBeanDefinition) original;
            beanClass = originalDefinition.getBeanClass();
        }
    }

    @Override
    public Object getBean() {
        return null;
    }

    public Class<?> getBeanClass() {
        return (Class<?>) this.beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
}
