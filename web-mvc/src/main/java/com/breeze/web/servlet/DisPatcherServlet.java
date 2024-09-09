package com.breeze.web.servlet;

import com.breeze.web.HandlerExecutionChain;
import com.breeze.web.HandlerMapping;
import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DisPatcherServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(DisPatcherServlet.class);

    private List<HandlerMapping> handlerMappings;

    private List<HandlerAdapter> handlerAdapters;

    @Override
    public void init() throws ServletException {
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("DisPatcherServlet receive request url: {}", request.getRequestURI());
        try {
            doService(request, response);
        } catch (Throwable e) {
            logger.error("{}", e.getMessage(), e);
        }
    }

    private void doService(HttpServletRequest request, HttpServletResponse response) {
        try {
            doDispatch0(request, response);
        } catch (Exception e) {
            logger.error("{}", e.getMessage(), e);
        }
    }

    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HandlerExecutionChain mappedHandler = getHandler(request);
        if (mappedHandler == null) {
            return;
        }
        HandlerAdapter ha = getHandlerAdapters(mappedHandler.getHandler());
        ModelAndView mv = ha.handle(request, response, mappedHandler.getHandler());
    }

    protected void doDispatch0(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应的Content-Type为application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 构建JSON字符串
        String jsonResponse = "{ \"message\": \"Hello, Breeze!\", \"status\": \"success\" }";

        // 将JSON字符串写入响应
        response.getWriter().write(jsonResponse);
    }


    private HandlerExecutionChain getHandler(HttpServletRequest request) {
        if (this.handlerMappings != null) {
            for (HandlerMapping mapping : handlerMappings) {
                HandlerExecutionChain handler = mapping.getHandler(request);
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;
    }

    protected HandlerAdapter getHandlerAdapters(Object handler) throws ServletException {
        if (this.handlerAdapters != null) {
            for (HandlerAdapter adapter : handlerAdapters) {
                if (adapter.supports(handler)) {
                    return adapter;
                }
            }
        }
        throw new ServletException("no HandlerAdapter found");
    }
}
