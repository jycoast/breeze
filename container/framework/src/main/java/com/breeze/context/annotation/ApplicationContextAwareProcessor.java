package com.breeze.context.annotation;

import com.breeze.beans.factory.ApplicationContextAware;
import com.breeze.beans.factory.config.BeanPostProcessor;
import com.breeze.context.ApplicationContext;

public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }
}
