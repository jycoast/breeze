package com.breeze.web.servlet;


import com.breeze.beans.factory.ApplicationContextAware;
import com.breeze.context.ApplicationContext;
import com.breeze.context.ConfigurableApplicationContext;
import com.breeze.context.event.ApplicationListener;
import com.breeze.context.event.ContextRefreshedEvent;
import com.breeze.web.context.WebApplicationContext;
import com.breeze.web.context.WebApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class FrameworkServlet extends HttpServlet implements ApplicationContextAware {

    private WebApplicationContext webApplicationContext;

    private Logger logger = LoggerFactory.getLogger(FrameworkServlet.class);

    public FrameworkServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        logger.info("init servlet");
        initServletBean();
    }

    protected void initServletBean() {
        try {
            this.webApplicationContext = initWebApplicationContext();
        } catch (Exception e) {
            logger.error("{}", e.getMessage(), e);
        }
    }

    private WebApplicationContext initWebApplicationContext() {
        WebApplicationContext rootContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        if (webApplicationContext == null) {
            webApplicationContext = rootContext;
        }
        onRefresh(webApplicationContext);
        return webApplicationContext;
    }

    private void configureAndRefreshWebApplicationContext() {
        ((ConfigurableApplicationContext) webApplicationContext).addApplicationListener(new ContextRefreshListener());
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
    public void setWebApplicationContext(ApplicationContext webApplicationContext) throws BeansException {
        this.webApplicationContext = (WebApplicationContext) webApplicationContext;
    }

    protected void onRefresh(ApplicationContext applicationContext) {

    }
}
