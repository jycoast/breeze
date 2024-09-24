package com.breeze.beans.factory.support;

import com.breeze.beans.factory.config.BeanDefinition;

public interface BeanNameGenerator {

    String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry registry);
}
