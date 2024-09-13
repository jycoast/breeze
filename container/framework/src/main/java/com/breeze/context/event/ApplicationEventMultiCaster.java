package com.breeze.context.event;

/**
 * Spring 事件广播器
 */
public interface ApplicationEventMultiCaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
