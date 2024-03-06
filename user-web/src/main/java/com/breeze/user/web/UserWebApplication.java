package com.breeze.user.web;

import com.breeze.boot.BreezeApplication;
import com.breeze.boot.autoConfigure.BreezeBootApplication;
import com.breeze.context.ApplicationContext;

@BreezeBootApplication
public class UserWebApplication {

    public static void main(String[] args) {
        ApplicationContext run = BreezeApplication.run(UserWebApplication.class, args);
    }
}
