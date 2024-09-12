package com.breeze.beans.factory;

import com.breeze.beans.factory.support.ListableBeanFactory;

import java.util.Map;

public class BeanFactoryUtils {

    public static <T> Map<String, T> beansOfTypeIncludingAncestors(ListableBeanFactory beanFactory, Class<T> type) {
        Map<String, T> beansOfType = beanFactory.getBeansOfType(type);
        return beansOfType;
    }
}
