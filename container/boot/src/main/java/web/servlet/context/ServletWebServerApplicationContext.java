package web.servlet.context;

import com.breeze.beans.factory.support.DefaultListableBeanFactory;
import com.breeze.context.support.GenericApplicationContext;
import com.breeze.web.context.WebApplicationContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import web.embedded.tomcat.TomcatServletWebServerFactory;
import web.servlet.ServletWebServerFactory;
import web.servlet.WebServer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

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
            this.webServer = webServerFactory.getWebServer();
            selfInitialize(this.servletContext);
        } catch (Exception e) {
            logger.error("webServer start failed:{}", e);
        }
    }

    private void selfInitialize(ServletContext servletContext) throws ServletException {
        prepareWebApplicationContext(servletContext);
    }

    protected void prepareWebApplicationContext(ServletContext servletContext) {
        try {
            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this);
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
