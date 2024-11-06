package web.embedded.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import web.servlet.WebServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TomcatWebServer implements WebServer {

    private static final Logger logger = LoggerFactory.getLogger(TomcatWebServer.class);

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
            initializeContext();
            tomcat.start();
            startDamonAwaitThread();
            logger.info("tomcat start success");
        } catch (Exception ex) {
            logger.error("tomcat start failed: {}", ex.getMessage(), ex);
            try {
                this.tomcat.stop();
                this.tomcat.destroy();
            } catch (Throwable e) {
                logger.error("tomcat stop failed: {}", e.getMessage(), e);
            }
        }
    }

    private void initializeContext() throws IOException {
        // 设置临时目录
        String tempDir = Files.createTempDirectory("tomcat").toString();
        System.setProperty("catalina.base", tempDir);
        tomcat.setBaseDir(tempDir);

        // 创建根目录下的Context
        String contextPath = "";
        String docBase = new File(".").getAbsolutePath();
        Context context = tomcat.addContext(contextPath, docBase);
        context.addLifecycleListener(new Tomcat.FixContextListener());
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
        tomcat.stop();
    }

    @Override
    public void getPort() throws Exception {
        tomcat.getConnector().getLocalPort();
    }
}
