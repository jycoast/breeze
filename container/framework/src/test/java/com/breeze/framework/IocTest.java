package com.breeze.framework;

import com.breeze.beans.factory.BeanFactory;
import com.breeze.beans.factory.config.BeanDefinition;
import com.breeze.beans.factory.support.DefaultListableBeanFactory;
import com.breeze.beans.factory.support.GenericBeanDefinition;
import org.junit.Test;


public class IocTest {

    @Test
    public void testBeanFactory() throws Exception {
        BeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new GenericBeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService);
    }
}
