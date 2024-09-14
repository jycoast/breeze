package com.breeze.beans.factory.support;

import com.breeze.beans.factory.BeanFactory;
import com.breeze.beans.factory.config.BeanDefinition;
import com.breeze.beans.factory.config.BeanPostProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private static final Log logger = LogFactory.getLog(AbstractBeanFactory.class);

    private final List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();

    @Override
    public Object getBean(String name) {
        return doGetBean(name, null, null);
    }

    protected <T> T doGetBean(String name, Class<?> requireType, Object[] args) {
        Object beanInstance = null;
        try {
            BeanDefinition beanDefinition = getBeanDefinition(name);
            beanInstance = createBean(name, beanDefinition);
        } catch (Exception e) {
            logger.error("doGetBean failed", e);
        }
        return (T) beanInstance;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {

    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws Exception;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws Exception;

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
}
