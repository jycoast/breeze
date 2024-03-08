package com.breeze.beans.factory.support;

import com.breeze.beans.factory.BeanFactory;
import com.breeze.beans.factory.config.BeanDefinition;
import com.breeze.beans.factory.config.BeanPostProcessor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();

    @Override
    public Object getBean(String beanName) throws Exception {
        Object singletonBean = getSingleton(beanName);
        if (singletonBean != null) {
            return singletonBean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {

    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws Exception;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws Exception;


    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

    }
}
