package com.breeze.boot.autoConfigure.web;

import com.breeze.web.servlet.DisPatcherServlet;
import org.springframework.util.Assert;
import web.servlet.ServletContextInitializer;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public abstract class ServletRegistrationBean<T extends Servlet> implements ServletContextInitializer {

    public void setServlet(T servlet) {
        this.servlet = servlet;
    }

    private static final String[] DEFAULT_MAPPINGS = {"/*"};

    private T servlet;

    protected String getDescription() {
        Assert.notNull(this.servlet, "Servlet must not be null");
        return "servlet " + servlet.getClass().getName();
    }

    public ServletRegistrationBean() {

    }

    public ServletRegistrationBean(DisPatcherServlet disPatcherServlet) {
        this.servlet = (T) disPatcherServlet;
    }

    @Override
    public void onStartup(ServletContext servletContext) {
        servletContext.log("ServletRegistrationBean onStartup");
        register(getDescription(), servletContext);
    }

    protected final void register(String description, ServletContext servletContext) {
        ServletRegistration.Dynamic registration = servletContext.addServlet("disPatcherServlet", servlet);
        configure(registration);
    }

    protected void configure(ServletRegistration.Dynamic registration) {
        registration.addMapping(DEFAULT_MAPPINGS);
    }
}
