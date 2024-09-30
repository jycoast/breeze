package web.servlet;

import javax.servlet.ServletContext;

public interface ServletContextInitializer {

    void onStartup(ServletContext servletContext);
}
