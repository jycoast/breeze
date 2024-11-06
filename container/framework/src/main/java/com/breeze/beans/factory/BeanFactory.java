package com.breeze.beans.factory;

import org.springframework.beans.BeansException;

/**
 * Bean工厂
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
