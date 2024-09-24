package com.breeze.boot;

import com.breeze.context.ApplicationContext;
import com.breeze.context.ConfigurableApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import web.servlet.context.ServletWebServerApplicationContext;

public class BreezeApplication {

    private static final Logger logger = LoggerFactory.getLogger(BreezeApplication.class);

    public static ApplicationContext run(Class<?> primarySource, String... args) {
        return new BreezeApplication().run();
    }

    public ConfigurableApplicationContext run() {
        ConfigurableApplicationContext context = null;
        try {
            context = createApplicationContext();
            refreshContext(context);
        } catch (Throwable ex) {
            logger.error("breeze web application start failed: {}", ex.getMessage(), ex);
        }

        return context;
    }

    private void refreshContext(ConfigurableApplicationContext context) throws Exception {
        try {
            context.refresh();
        } catch (Exception e) {
            throw new Exception("context refresh failed:{}", e);
        }
    }

    private ConfigurableApplicationContext createApplicationContext() {
        // 创建 AnnotationConfigServletWebServerApplicationContext 实例
        return new AnnotationConfigServletWebServerApplicationContext();
    }
}
