package com.breeze.beans.factory;

import com.breeze.context.ApplicationContext;
import org.springframework.beans.BeansException;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
