package com.breeze.beans.factory.support;

import com.breeze.beans.factory.config.BeanDefinition;
import com.breeze.beans.factory.config.BeanPostProcessor;
import com.breeze.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.breeze.context.annotation.ApplicationContextAwareProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;

/**
 * 具有自动装配能力的Bean工厂
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory {

    private static final Logger logger = LoggerFactory.getLogger(AbstractAutoWireCapableBeanFactory.class);

    @Override
    protected Object createBean(String beanName, RootBeanDefinition mbd) throws Exception {
        Object bean;
        try {
            bean = resolveBeforeInstantiation(beanName, mbd);
            // 判断是否返回代理对象
            if (bean != null) {
                return bean;
            }
            bean = doCreateBean(beanName, mbd);

            applyPropertyValues(beanName, bean, mbd);

            bean = initializeBean(beanName, bean, mbd);
        } catch (Exception e) {
            logger.error("create bean failed：{}", e.getMessage(), e);
            throw new Exception("create bean failed");
        }
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 初始化bean
     */
    private Object initializeBean(String beanName, Object bean, RootBeanDefinition mbd) {
        return bean;
    }

    /**
     * 属性填充
     *
     */
    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            if (processor instanceof ApplicationContextAwareProcessor) {
                processor.postProcessBeforeInitialization(bean, beanName);
            }
        }
    }

    protected Object resolveBeforeInstantiation(String beanName, RootBeanDefinition mbd) {
        Object bean = applyBeanPostProcessorBeforeInstantiation(mbd.getBeanClass(), beanName);
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

    protected Object doCreateBean(String beanName, RootBeanDefinition mbd) throws Exception {
        return createBeanInstance(beanName, mbd);
    }

    private Object createBeanInstance(String beanName, RootBeanDefinition mbd) throws NoSuchMethodException {
        Class<?> beanClass = mbd.getBeanClass();
        Object beanInstance = null;
        if (beanClass != null) {
            Constructor<?> declaredConstructor = beanClass.getDeclaredConstructor();
            beanInstance = BeanUtils.instantiateClass(declaredConstructor);
        }
        return beanInstance;
    }
}
