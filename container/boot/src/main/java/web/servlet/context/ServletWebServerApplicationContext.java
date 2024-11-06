package web.servlet.context;

import com.breeze.beans.factory.config.ConfigurableListableBeanFactory;
import com.breeze.beans.factory.support.DefaultListableBeanFactory;
import com.breeze.boot.autoConfigure.web.DispatcherServletRegistrationBean;
import com.breeze.context.support.GenericApplicationContext;
import com.breeze.web.context.WebApplicationContext;
import com.breeze.web.servlet.DisPatcherServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import web.embedded.tomcat.TomcatServletWebServerFactory;
import web.servlet.ServletContextInitializer;
import web.servlet.ServletWebServerFactory;
import web.servlet.WebServer;

import javax.servlet.ServletContext;
import java.util.Collection;
import java.util.Collections;

public class ServletWebServerApplicationContext extends GenericApplicationContext implements WebApplicationContext {

    private static final Log logger = LogFactory.getLog(ServletWebServerApplicationContext.class);

    private volatile WebServer webServer;

    private ServletContext servletContext;

    public ServletWebServerApplicationContext() {

    }

    public ServletWebServerApplicationContext(ServletContext servletContext) {
        super();
        this.servletContext = servletContext;
    }

    public ServletWebServerApplicationContext(DefaultListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    protected void onRefresh() throws Exception {
        try {
            createWebServer();
        } catch (Throwable ex) {
            throw new Exception("unable to start web server");
        }
    }

    private void createWebServer() {
        ServletWebServerFactory webServerFactory = getWebServerFactory();
        try {
            this.webServer = webServerFactory.getWebServer(getSelfInitializer());
            selfInitialize(this.servletContext);
        } catch (Exception e) {
            logger.error("webServer start failed:{}", e);
        }
    }

    private ServletContextInitializer getSelfInitializer() {
        return this::selfInitialize;
    }

    private void selfInitialize(ServletContext servletContext) {
        prepareWebApplicationContext(servletContext);
        for (ServletContextInitializer initializer : getServletContextInitializerBeans()) {
            initializer.onStartup(servletContext);
        }
    }

    private Collection<ServletContextInitializer> getServletContextInitializerBeans() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        DispatcherServletRegistrationBean dispatcherServletRegistrationBean =
                beanFactory.getBean("dispatcherServletRegistrationBean", DispatcherServletRegistrationBean.class);
        DisPatcherServlet disPatcherServlet = beanFactory.getBean("disPatcherServlet", DisPatcherServlet.class);
        dispatcherServletRegistrationBean.setServlet(disPatcherServlet);
        return Collections.singleton(dispatcherServletRegistrationBean);
    }

    protected void prepareWebApplicationContext(ServletContext servletContext) {
        servletContext.log("Initializing Spring embedded WebApplicationContext");
        try {
            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this);
            setServletContext(servletContext);
        } catch (RuntimeException | Error ex) {
            logger.error("Context initialization failed", ex);
            servletContext.setAttribute(ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, ex);
            throw ex;
        }
    }

    protected ServletWebServerFactory getWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
