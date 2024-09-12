package com.breeze.web.servlet;


import com.breeze.context.ApplicationContext;
import com.breeze.context.event.ApplicationListener;
import com.breeze.context.event.ContextRefreshedEvent;

import javax.servlet.http.HttpServlet;

public class FrameworkServlet extends HttpServlet {

    private class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            FrameworkServlet.this.onApplicationEvent(event);
        }
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        onRefresh(event.getApplicationContext());
    }

    protected void onRefresh(ApplicationContext applicationContext) {

    }
}
