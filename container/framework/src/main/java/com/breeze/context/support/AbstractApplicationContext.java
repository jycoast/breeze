package com.breeze.context.support;

import com.breeze.beans.factory.config.ConfigurableListableBeanFactory;
import com.breeze.context.ConfigurableApplicationContext;
import com.breeze.context.annotation.ApplicationContextAwareProcessor;
import com.breeze.context.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    private Logger logger = LoggerFactory.getLogger(AbstractApplicationContext.class);

    private ApplicationEventMultiCaster applicationEventMultiCaster;

    private Set<ApplicationListener<?>> applicationListeners;

    @Override
    public void refresh() throws Exception {

        // 准备上下文
        prePareRefresh();

        // 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = ObtainFreshBeanFactory();

        // 注册内建Bean
        prepareBeanFactory(beanFactory);

        try {
            // 注册BeanPostProcessors
            registerBeanPostProcessors(beanFactory);

            // 注册事件传播器
            initApplicationEventMultiCaster();

            // 启动
            onRefresh();

            // 注册监听器
            registerListeners();

            // 初始化非懒加载的Bean
            finishBeanFactoryInitialization(beanFactory);

            // 发布启动完成事件
            finishRefresh();
        } catch (Throwable err) {
            logger.error("Exception encountered during context initialization - cancelling refresh attempt: {}", err.getMessage());
        }
    }

    private void initApplicationEventMultiCaster() throws Exception {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        this.applicationEventMultiCaster = new SimpleApplicationEventMulticaster(beanFactory);
    }


    private ConfigurableListableBeanFactory ObtainFreshBeanFactory() throws Exception {
        return getBeanFactory();
    }

    private void prePareRefresh() {

    }

    private void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {

    }

    protected void onRefresh() throws Exception {

    }

    private void registerListeners() {
        // 1.内部有的
        // getApplicationListeners().forEach(applicationListener -> getApplicationEventMultiCaster().addApplicationListener(applicationListener));
        // 2.TODO 容器中的
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        getApplicationEventMultiCaster().addApplicationListener(listener);
    }

    protected Set<ApplicationListener<?>> getApplicationListeners() {
        return this.applicationListeners;
    }

    private void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.preInitializationSingletons();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    public void publishEvent(ApplicationEvent event) {
        getApplicationEventMultiCaster().multicastEvent(event);
    }

    public ApplicationEventMultiCaster getApplicationEventMultiCaster() {
        return this.applicationEventMultiCaster;
    }

    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this);
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
    }

    public abstract ConfigurableListableBeanFactory getBeanFactory() throws Exception;
}
