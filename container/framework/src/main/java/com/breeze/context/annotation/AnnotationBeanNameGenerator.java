package com.breeze.context.annotation;

import com.breeze.beans.factory.config.BeanDefinition;
import com.breeze.beans.factory.support.BeanDefinitionRegistry;
import com.breeze.beans.factory.support.BeanNameGenerator;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

public class AnnotationBeanNameGenerator implements BeanNameGenerator {

    /**
     * 单例
     */
    public static final AnnotationBeanNameGenerator INSTANCE = new AnnotationBeanNameGenerator();

    @Override
    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry registry) {
        String beanClassName = beanDefinition.getBean().getClass().getName();
        String shortClassName = ClassUtils.getShortName(beanClassName);
        return Introspector.decapitalize(shortClassName);
    }
}
