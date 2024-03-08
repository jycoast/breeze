package com.breeze.context;

/**
 * 可以配置的应用上下文
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws Exception;
}
