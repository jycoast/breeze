package com.breeze.context.annotation;

import com.breeze.beans.factory.support.DefaultListableBeanFactory;
import com.breeze.context.support.GenericApplicationContext;

public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {

    public AnnotationConfigApplicationContext(DefaultListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    AnnotationConfigApplicationContext() {
        super(null);
    }

    public AnnotationConfigApplicationContext(String... basePackages) {
        this();
    }
}
