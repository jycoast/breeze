package web.embedded.tomcat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.servlet.ServletContextInitializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * servlet 容器启动器
 */
public class TomcatStarter implements ServletContainerInitializer {

    private static final Logger logger = LoggerFactory.getLogger(TomcatStarter.class);

    private final ServletContextInitializer[] servletContextInitializers;

    public TomcatStarter(ServletContextInitializer[] servletContextInitializers) {
        this.servletContextInitializers = servletContextInitializers;
    }

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        try {
            for (ServletContextInitializer servletContextInitializer : servletContextInitializers) {
                servletContextInitializer.onStartup(ctx);
            }
        } catch (Exception e) {
            logger.error("Error starting Tomcat context. Exception:", e);
        }
    }
}
