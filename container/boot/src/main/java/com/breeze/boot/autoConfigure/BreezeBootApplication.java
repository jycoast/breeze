package com.breeze.boot.autoConfigure;

import java.lang.annotation.*;

/**
 * 启动类注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BreezeBootApplication {
}
