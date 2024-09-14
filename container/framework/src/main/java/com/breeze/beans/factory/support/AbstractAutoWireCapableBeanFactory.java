package com.breeze.beans.factory.support;

import com.breeze.beans.factory.config.BeanDefinition;
import com.breeze.beans.factory.config.BeanPostProcessor;
import com.breeze.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.breeze.context.annotation.ApplicationContextAwareProcessor;

/**
 * 具有自动装配能力的Bean工厂
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws Exception {
        Object bean;
        try {
            bean = resolveBeforeInstantiation(beanName, beanDefinition);
            // 判断是否返回代理对象
            if (bean != null) {
                return bean;
            }
            bean = doCreateBean(beanName, beanDefinition);

            applyPropertyValues(beanName, bean, beanDefinition);

            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new Exception("create bean failed");
        }
        addSingleton(beanName, bean);
        return bean;
    }

    private Object createBeanInstance(String beanName, BeanDefinition beanDefinition) {
        return null;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        return null;
    }

    /**
     * 属性填充
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            if (processor instanceof ApplicationContextAwareProcessor) {
                processor.postProcessBeforeInitialization(bean, beanName);
            }
        }
    }

    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        Object bean = applyBeanPostProcessorBeforeInstantiation(beanDefinition.getBean().getClass(), beanName);
        if (bean != null) {
            bean = applyBeanPostProcessorAfterInstantiation(bean, beanName);
        }
        return bean;
    }

    private Object applyBeanPostProcessorBeforeInstantiation(Class<?> beanClass, String beanName) {
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            if (processor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) processor).postProcessBeforeInstantiation(beanClass, beanName);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private Object applyBeanPostProcessorAfterInstantiation(Object bean, String beanName) {
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            if (processor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) processor).postProcessAfterInstantiation(bean, beanName);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    protected Object doCreateBean(String beanName, BeanDefinition bd) throws Exception {
        return null;
    }
}
