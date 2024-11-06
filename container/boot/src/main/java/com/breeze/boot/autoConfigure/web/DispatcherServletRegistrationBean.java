package com.breeze.boot.autoConfigure.web;

import com.breeze.web.servlet.DisPatcherServlet;

public class DispatcherServletRegistrationBean extends ServletRegistrationBean<DisPatcherServlet> {

    public DispatcherServletRegistrationBean() {
        super();
    }

    public DispatcherServletRegistrationBean(DisPatcherServlet disPatcherServlet) {
        super(disPatcherServlet);
    }
}
