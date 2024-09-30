package web.servlet;

public interface ServletWebServerFactory {

    WebServer getWebServer(ServletContextInitializer... initializers);
}
