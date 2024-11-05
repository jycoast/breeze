package com.breeze.boot.autoConfigure.web.servlet;

import com.breeze.boot.autoConfigure.web.DispatcherServletRegistrationBean;
import com.breeze.context.annotation.Bean;
import com.breeze.context.annotation.Configuration;
import com.breeze.web.servlet.DisPatcherServlet;

/**
 * 自动装配 DispatcherServlet
 */
@Configuration
public class DispatcherServletAutoConfiguration {

    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DisPatcherServlet disPatcherServlet) {
        DispatcherServletRegistrationBean registration = new DispatcherServletRegistrationBean(disPatcherServlet);
        return registration;
    }

    @Bean
    public DisPatcherServlet disPatcherServlet() {
        return new DisPatcherServlet();
    }
}
