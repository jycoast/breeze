package com.breeze.context.annotation;

import com.breeze.beans.factory.support.BeanDefinitionRegistry;

public class AnnotatedBeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }
}
