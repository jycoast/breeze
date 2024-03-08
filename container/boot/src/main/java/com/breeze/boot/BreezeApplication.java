package com.breeze.boot;

import com.breeze.context.ApplicationContext;
import com.breeze.context.ConfigurableApplicationContext;
import org.springframework.beans.BeanUtils;
import web.servlet.context.ServletWebServerApplicationContext;

public class BreezeApplication {

    public static ApplicationContext run(Class<?> primarySource, String... args) {
        return new BreezeApplication().run();
    }

    public ConfigurableApplicationContext run() {
        ConfigurableApplicationContext context = null;
        try {
            context = createApplicationContext();
            refreshContext(context);
        } catch (Throwable ex) {

        }

        return context;
    }

    private void refreshContext(ConfigurableApplicationContext context) throws Exception {
        try {
            context.refresh();
        } catch (Exception e) {
            throw new Exception("context refresh failed");
        }
    }

    private ConfigurableApplicationContext createApplicationContext() {
        // 创建 ServletWebServerApplicationContext 实例
        return BeanUtils.instantiateClass(ServletWebServerApplicationContext.class);
    }
}
