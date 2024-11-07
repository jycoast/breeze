package com.breeze.web.servlet;

import com.breeze.beans.factory.BeanFactoryUtils;
import com.breeze.beans.factory.support.ListableBeanFactory;
import com.breeze.context.ApplicationContext;
import com.breeze.web.HandlerExecutionChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DisPatcherServlet extends FrameworkServlet {

    private final Logger logger = LoggerFactory.getLogger(DisPatcherServlet.class);

    private List<HandlerMapping> handlerMappings;

    private List<HandlerAdapter> handlerAdapters;

    public DisPatcherServlet() {
        super();
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
            doDispatch(request, response);
        } catch (Exception e) {
            logger.error("{}", e.getMessage(), e);
        }
    }

    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HandlerExecutionChain mappedHandler = getHandler(request);
        if (mappedHandler == null) {
            return;
        }
        HandlerAdapter ha = getHandlerAdapters(mappedHandler.getHandler());
        ModelAndView mv = ha.handle(request, response, mappedHandler.getHandler());
        processDispatchResult(request, response, mappedHandler, mv);
    }

    private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
                                       HandlerExecutionChain mappedHandler, ModelAndView mv) throws Exception {
        // 设置响应的Content-Type为application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // 构建JSON字符串
        String jsonResponse = "{ \"message\": \"Hello, Breeze!\", \"status\": \"success\" }";
        // 将JSON字符串写入响应
        response.getWriter().write(jsonResponse);
        if (mv != null) {
            View view = mv.getView();
            view.render(mv.getModelMap(), request, response);
        }
    }

    @Override
    protected void onRefresh(ApplicationContext context) {
        initStrategies(context);
    }

    private void initStrategies(ApplicationContext context) {
        initHandlerMappings(context);
        initHandlerAdapters(context);
    }

    private void initHandlerMappings(ApplicationContext context) {
        logger.info("load HandlerMapping");
        this.handlerMappings = null;
        Map<String, HandlerMapping> matchingBeans =
                BeanFactoryUtils.beansOfTypeIncludingAncestors((ListableBeanFactory) context, HandlerMapping.class);
        if (!matchingBeans.isEmpty()) {
            handlerMappings = new ArrayList<>(matchingBeans.values());
        }
    }

    private void initHandlerAdapters(ApplicationContext context) {
        logger.info("load HandlerAdapter");
        this.handlerAdapters = null;
        Map<String, HandlerAdapter> matchingBeans =
                BeanFactoryUtils.beansOfTypeIncludingAncestors((ListableBeanFactory) context, HandlerAdapter.class);
        if (!matchingBeans.isEmpty()) {
            handlerAdapters = new ArrayList<>(matchingBeans.values());
        }
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
