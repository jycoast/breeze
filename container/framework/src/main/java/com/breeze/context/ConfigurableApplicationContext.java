package com.breeze.context;

import com.breeze.context.event.ApplicationListener;

/**
 * 可以配置的应用上下文
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws Exception;

    void addApplicationListener(ApplicationListener<?> listener);
}
