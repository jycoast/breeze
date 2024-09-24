package com.breeze.context.support;

import com.breeze.beans.factory.config.BeanDefinition;
import com.breeze.beans.factory.config.ConfigurableListableBeanFactory;
import com.breeze.beans.factory.support.BeanDefinitionRegistry;
import com.breeze.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.Assert;


public class GenericApplicationContext extends AbstractApplicationContext implements BeanDefinitionRegistry {

    private final DefaultListableBeanFactory beanFactory;

    public GenericApplicationContext() {
        this.beanFactory = new DefaultListableBeanFactory();
    }

    public GenericApplicationContext(DefaultListableBeanFactory beanFactory) {
        Assert.notNull(beanFactory, "BeanFactory must not be null");
        this.beanFactory = beanFactory;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {

    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws Exception {
        return this.beanFactory;
    }
}
