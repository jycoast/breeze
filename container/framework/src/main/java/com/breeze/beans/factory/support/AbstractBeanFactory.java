package com.breeze.beans.factory.support;

import com.breeze.beans.factory.BeanFactory;
import com.breeze.beans.factory.config.BeanDefinition;
import com.breeze.beans.factory.config.BeanPostProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private static final Log logger = LogFactory.getLog(AbstractBeanFactory.class);

    private final List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();

    public Object getBean(String name) {
        return doGetBean(name, null, null);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return doGetBean(name, requiredType, null);
    }

    protected <T> T doGetBean(String name, Class<?> requireType, Object[] args) {
        Object beanInstance = null;
        try {
            RootBeanDefinition mbd;
            if (requireType != null) {
                mbd = getMergedLocalBeanDefinition(name, requireType);
            } else {
                mbd = getMergedLocalBeanDefinition(name);
            }
            beanInstance = createBean(name, mbd);
        } catch (Exception e) {
            logger.error("doGetBean failed", e);
        }
        return (T) beanInstance;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws Exception;

    protected abstract Object createBean(String beanName, RootBeanDefinition beanDefinition) throws Exception;

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    private RootBeanDefinition getMergedLocalBeanDefinition(String name) throws Exception {
        BeanDefinition beanDefinition = getBeanDefinition(name);
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(beanDefinition);
        return rootBeanDefinition;
    }

    private RootBeanDefinition getMergedLocalBeanDefinition(String name, Class<?> requiredType) throws Exception {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(null);
        rootBeanDefinition.setBeanClass(requiredType);
        return rootBeanDefinition;
    }
}
