package web.embedded.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import web.servlet.ServletContextInitializer;
import web.servlet.ServletWebServerFactory;
import web.servlet.WebServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Set;

public class TomcatServletWebServerFactory implements ServletWebServerFactory {

    private static final Set<Class<?>> NO_CLASSES = Collections.emptySet();

    public static final String DEFAULT_PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";

    public WebServer getWebServer(ServletContextInitializer... initializers) {
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector(DEFAULT_PROTOCOL);
        connector.setThrowOnFailure(true);
        connector.setPort(8080);
        tomcat.getService().addConnector(connector);
        tomcat.getHost().setAutoDeploy(false);
        Host host = tomcat.getHost();

        // 准备tomcat上下文
        prepareContext(host, initializers);
        return new TomcatWebServer(tomcat, true);
    }

    private void prepareContext(Host host, ServletContextInitializer... initializers) {
        TomcatEmbeddedContext context = new TomcatEmbeddedContext();
        context.setName("/");
        File docBase = createTempDir("tomcat-docbase", 8080);
        context.setDocBase(docBase.getAbsolutePath());
        host.addChild(context);
        configContext(context, initializers);
    }

    private void configContext(Context context, ServletContextInitializer... initializers) {
        TomcatStarter starter = new TomcatStarter(initializers);
        context.addServletContainerInitializer(starter, Collections.singleton(TomcatStarter.class));
    }

    protected final File createTempDir(String prefix, int port) {
        try {
            File tempDir = Files.createTempDirectory(prefix + "." + port + ".").toFile();
            tempDir.deleteOnExit();
            return tempDir;
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"), ex);
        }
    }
}
