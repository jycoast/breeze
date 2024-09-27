package com.breeze.boot;

import org.junit.Test;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;

public class AnnotationConfigServletWebApplicationContextTest {

    @Test
    public void refresh() {
        AnnotationConfigServletWebApplicationContext applicationContext = new AnnotationConfigServletWebApplicationContext("com.breeze");
        applicationContext.refresh();
    }
}
