package web.servlet.context;

import com.breeze.context.support.AbstractApplicationContext;
import web.embedded.tomcat.TomcatServletWebServerFactory;
import web.servlet.ServletWebServerFactory;
import web.servlet.WebServer;

public class ServletWebServerApplicationContext extends AbstractApplicationContext {

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
        this.webServer = webServerFactory.getWebServer();
        try {
            webServer.start();
        } catch (Exception e) {
            // throw new Exception("webServer start failed");
        }
    }

    protected ServletWebServerFactory getWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
