package web.embedded.tomcat;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import web.servlet.ServletWebServerFactory;
import web.servlet.WebServer;

public class TomcatServletWebServerFactory implements ServletWebServerFactory {

    public static final String DEFAULT_PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";

    public WebServer getWebServer() {
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector(DEFAULT_PROTOCOL);
        connector.setThrowOnFailure(true);
        tomcat.getService().addConnector(connector);
        tomcat.getHost().setAutoDeploy(false);

        return new TomcatWebServer(tomcat);
    }
}
