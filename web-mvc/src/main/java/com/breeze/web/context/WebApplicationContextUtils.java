package com.breeze.web.context;

import javax.servlet.ServletContext;

public class WebApplicationContextUtils {

    public static WebApplicationContext getWebApplicationContext(ServletContext sc) {
        return getWebApplicationContext(sc, WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    }

    public static WebApplicationContext getWebApplicationContext(ServletContext sc, String root) {
        return (WebApplicationContext) sc.getAttribute(root);
    }
}
