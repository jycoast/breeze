package com.breeze.context.support;

import com.breeze.beans.factory.config.ConfigurableListableBeanFactory;
import com.breeze.context.ConfigurableApplicationContext;

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

    private void finishRefresh() {

    }

    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this);
    }

    public abstract ConfigurableListableBeanFactory getBeanFactory() throws Exception;
}
