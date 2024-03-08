package com.breeze.context.support;

import com.breeze.beans.factory.config.ConfigurableListableBeanFactory;
import com.breeze.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.Assert;


public class GenericApplicationContext extends AbstractApplicationContext {

    private final DefaultListableBeanFactory beanFactory;

    public GenericApplicationContext(DefaultListableBeanFactory beanFactory) {
        Assert.notNull(beanFactory, "BeanFactory must not be null");
        this.beanFactory = beanFactory;
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws Exception {
        return this.beanFactory;
    }
}
