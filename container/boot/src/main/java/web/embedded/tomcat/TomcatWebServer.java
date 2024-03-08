package web.embedded.tomcat;

import org.apache.catalina.startup.Tomcat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import web.servlet.WebServer;

public class TomcatWebServer implements WebServer {

    private static final Log logger = LogFactory.getLog(TomcatWebServer.class);

    private final Tomcat tomcat;

    private boolean autoStart = false;

    public TomcatWebServer(Tomcat tomcat) {
        this.tomcat = tomcat;
    }

    public TomcatWebServer(Tomcat tomcat, boolean autoStart) {
        Assert.notNull(tomcat, "tomcat server must not be null");
        this.tomcat = tomcat;
        this.autoStart = autoStart;
        initialize();
    }

    private void initialize() {
        try {
            tomcat.start();
            startDamonAwaitThread();
        } catch (Exception ex) {
            logger.error("tomcat start failed");
            ex.printStackTrace();
            try {
                this.tomcat.stop();
                this.tomcat.destroy();
            } catch (Throwable e) {
                logger.error("tomcat stop failed");
                e.printStackTrace();
            }
        }
    }

    private void startDamonAwaitThread() {
        Thread awaitThread = new Thread("Tomcat daemon thread") {
            @Override
            public void run() {
                TomcatWebServer.this.tomcat.getServer().await();
            }
        };

        awaitThread.setContextClassLoader(getClass().getClassLoader());
        awaitThread.setDaemon(false);
        awaitThread.start();
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
