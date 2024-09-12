package com.breeze.beans.factory.support;

import com.breeze.beans.factory.BeanFactory;
import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    <T> Map<String, T> getBeansOfType(@Nullable Class<T> type) throws BeansException;
}
