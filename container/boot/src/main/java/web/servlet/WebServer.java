package web.servlet;

public interface WebServer {

    void start() throws Exception;

    void stop() throws Exception;

    void getPort() throws Exception;
}
