package com.breeze.beans.factory.support;

import com.breeze.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonBeanObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonBeanObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonBean) {
        singletonBeanObjects.put(beanName, singletonBean);
    }
}
