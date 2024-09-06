package com.breeze.user.web;

import com.breeze.boot.BreezeApplication;
import com.breeze.boot.autoConfigure.BreezeBootApplication;
import com.breeze.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BreezeBootApplication
public class UserWebApplication {

    private static final Logger logger = LoggerFactory.getLogger(UserWebApplication.class);

    public static void main(String[] args) {
        logger.info("breeze web application start");
        ApplicationContext run = BreezeApplication.run(UserWebApplication.class, args);
    }
}
