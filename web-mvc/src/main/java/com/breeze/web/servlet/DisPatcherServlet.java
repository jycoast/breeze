package com.breeze.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisPatcherServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(DisPatcherServlet.class);

    @Override
    public void init() throws ServletException {
        logger.info("init method invoke!");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        logger.info("service method invoke!");
        try {
            doService(request, response);
        } catch (Throwable e) {
            logger.error("{}", e.getMessage(), e);
        }
    }

    private void doService(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        doGet((HttpServletRequest) request, (HttpServletResponse) response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应的Content-Type为application/json
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 构建JSON字符串
        String jsonResponse = "{ \"message\": \"Hello, World!\", \"status\": \"success\" }";

        // 将JSON字符串写入响应
        resp.getWriter().write(jsonResponse);
    }
}
