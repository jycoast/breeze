package com.breeze.boot.autoConfigure.web;

import com.breeze.web.servlet.DisPatcherServlet;
import web.servlet.ServletContextInitializer;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

public abstract class ServletRegistrationBean<T extends Servlet> implements ServletContextInitializer {

    private T servlet;

    public ServletRegistrationBean(DisPatcherServlet disPatcherServlet) {
        this.servlet = (T) disPatcherServlet;
    }

    @Override
    public void onStartup(ServletContext servletContext) {
        configure(servletContext);
    }

    protected void configure(ServletContext servletContext) {
//        servletContext.addServlet("", servlet);
    }
}
