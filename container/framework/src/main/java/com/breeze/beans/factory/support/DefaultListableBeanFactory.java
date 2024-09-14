package com.breeze.beans.factory.support;

import com.breeze.beans.factory.config.BeanDefinition;
import com.breeze.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory extends AbstractAutoWireCapableBeanFactory
        implements ConfigurableListableBeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<String> beanDefinitionNames = new ArrayList<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new Exception("no bean named" + beanName + "is defined");
        }
        return beanDefinition;
    }

    @Override
    public void preInitializationSingletons() {
        List<String> beanNames = new ArrayList<>(beanDefinitionNames);
        for (String beanName : beanNames) {
            getBean(beanName);
        }
    }
}
