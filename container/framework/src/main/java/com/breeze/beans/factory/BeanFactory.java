package com.breeze.beans.factory;

/**
 * Bean工厂
 */
public interface BeanFactory {

    Object getBean(String beanName) throws Exception;
}
