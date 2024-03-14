package com.breeze.web.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class DisPatcherServlet extends HttpServlet {

    private final Log logger = LogFactory.getLog(DisPatcherServlet.class);

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        logger.info("service method invoke!");
        try {
            doService(request, response);
        } catch (Throwable e) {
//            logger.error("{}", e.getMessage(), e);
        }
    }


    private void doService(ServletRequest request, ServletResponse response) {

    }
}
