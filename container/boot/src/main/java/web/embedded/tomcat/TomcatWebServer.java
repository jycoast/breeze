package web.embedded.tomcat;

import org.apache.catalina.startup.Tomcat;
import web.servlet.WebServer;

public class TomcatWebServer implements WebServer {

    private final Tomcat tomcat;


    public TomcatWebServer(Tomcat tomcat) {
        this.tomcat = tomcat;
    }

    @Override
    public void start() throws Exception {
        tomcat.start();
    }

    @Override
    public void stop() throws Exception {
        tomcat.start();
    }

    @Override
    public void getPort() throws Exception {

    }
}
