package com.breeze.beans.factory.support;

import com.breeze.beans.factory.config.BeanDefinition;

public class RootBeanDefinition extends AbstractBeanDefinition {

    RootBeanDefinition() {
        super(null);
    }

    RootBeanDefinition(BeanDefinition original) {
        super(original);
    }
}
