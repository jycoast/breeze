package com.breeze.context.support;

import com.breeze.beans.factory.config.ConfigurableListableBeanFactory;
import com.breeze.context.ConfigurableApplicationContext;
import com.breeze.context.event.ApplicationEvent;
import com.breeze.context.event.ContextRefreshedEvent;

public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

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

            // 启动
            onRefresh();

            // 初始化非懒加载的Bean
            finishBeanFactoryInitialization(beanFactory);

            // 发布启动完成事件
            finishRefresh();
        } catch (Exception ignored) {

        }
    }


    private ConfigurableListableBeanFactory ObtainFreshBeanFactory() {
        return null;
    }

    private void prePareRefresh() {

    }

    private void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {

    }

    protected void onRefresh() throws Exception {

    }

    private void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.preInitializationSingletons();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    public void publishEvent(ApplicationEvent event) {

    }

    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this);
    }

    public abstract ConfigurableListableBeanFactory getBeanFactory() throws Exception;
}
