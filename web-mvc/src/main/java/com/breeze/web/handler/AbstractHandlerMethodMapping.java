package com.breeze.web.handler;

import com.breeze.web.HandlerExecutionChain;
import com.breeze.web.method.HandlerMethod;
import com.breeze.web.servlet.HandlerMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.breeze.beans.factory.InitializingBean;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractHandlerMethodMapping<T> implements HandlerMapping, InitializingBean {

    private Logger logger = LoggerFactory.getLogger(AbstractHandlerMethodMapping.class);

    MappingRegistry getMappingRegistry() {
        return mappingRegistry;
    }

    private final MappingRegistry mappingRegistry = new MappingRegistry();

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initHandlerMethods();
    }

    protected void initHandlerMethods() {
        logger.info("load handler methods");
    }

    class MappingRegistry {

        private final Map<String, List<HandlerMethod>> nameLookup = new ConcurrentHashMap<>();

        public void register(T mapping, Object handler, Method method) {
            HandlerMethod handlerMethod = createHandlerMethod(handler, method);
        }

        private HandlerMethod createHandlerMethod(Object handler, Method method) {
            return null;
        }
    }
}
