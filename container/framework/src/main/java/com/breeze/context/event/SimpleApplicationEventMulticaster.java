package com.breeze.context.event;

import com.breeze.beans.factory.BeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class SimpleApplicationEventMulticaster implements ApplicationEventMultiCaster {

    private Logger logger = LoggerFactory.getLogger(SimpleApplicationEventMulticaster.class);


    private Set<ApplicationListener<?>> applicationListeners;

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        if (this.applicationListeners == null) {
            this.applicationListeners = new HashSet<>();
        }
        applicationListeners.add(listener);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        Set<ApplicationListener<?>> applicationListeners = getApplicationListener(event);
        if (applicationListeners == null || applicationListeners.isEmpty()) {
            return;
        }
        for (ApplicationListener<?> applicationListener : applicationListeners) {
            invokeListener((ApplicationListener<ApplicationEvent>) applicationListener, event);
        }
    }

    protected void invokeListener(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {
        try {
            listener.onApplicationEvent(event);
        } catch (Throwable err) {
            logger.error("Error invoking listener " + listener, err);
        }
    }

    private Set<ApplicationListener<?>> getApplicationListener(ApplicationEvent event) {
        return this.applicationListeners;
    }
}
