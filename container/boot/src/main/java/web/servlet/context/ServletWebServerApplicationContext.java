package web.servlet.context;

import com.breeze.beans.factory.config.ConfigurableListableBeanFactory;
import com.breeze.beans.factory.support.DefaultListableBeanFactory;
import com.breeze.context.support.AbstractApplicationContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import web.embedded.tomcat.TomcatServletWebServerFactory;
import web.servlet.ServletWebServerFactory;
import web.servlet.WebServer;

public class ServletWebServerApplicationContext extends AbstractApplicationContext {


    private static final Log logger = LogFactory.getLog(ServletWebServerApplicationContext.class);

    private volatile WebServer webServer;

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
        } catch (Exception e) {
            logger.error("webServer start failed");
        }
    }

    protected ServletWebServerFactory getWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws Exception {
        return new DefaultListableBeanFactory();
    }
}
