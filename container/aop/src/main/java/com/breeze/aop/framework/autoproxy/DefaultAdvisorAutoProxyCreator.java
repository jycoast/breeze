package com.breeze.aop.framework.autoproxy;

import com.breeze.aop.ProxyFactory;
import com.breeze.beans.factory.BeanFactory;
import com.breeze.beans.factory.BeanFactoryAware;
import com.breeze.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.breeze.beans.factory.support.DefaultListableBeanFactory;

public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        beanFactory = this.beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        ProxyFactory proxyFactory = new ProxyFactory(null);
        // 返回代理对象
        return proxyFactory.getProxy();
    }

    @Override
    public Object postProcessAfterInstantiation(Object bean, String beanName) {
        return null;
    }
}
