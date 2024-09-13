package com.breeze.web.servlet;


import com.breeze.beans.factory.ApplicationContextAware;
import com.breeze.context.ApplicationContext;
import com.breeze.context.ConfigurableApplicationContext;
import com.breeze.context.event.ApplicationListener;
import com.breeze.context.event.ContextRefreshedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class FrameworkServlet extends HttpServlet implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Logger logger = LoggerFactory.getLogger(FrameworkServlet.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("FrameworkServlet init");
        configureAndRefreshWebApplicationContext();
    }

    private void configureAndRefreshWebApplicationContext() {
        ((ConfigurableApplicationContext) applicationContext).addApplicationListener(new ContextRefreshListener());
    }

    private class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            FrameworkServlet.this.onApplicationEvent(event);
        }
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        onRefresh(event.getApplicationContext());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected void onRefresh(ApplicationContext applicationContext) {

    }
}
