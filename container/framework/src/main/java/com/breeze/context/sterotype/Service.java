package com.breeze.context.sterotype;

import java.lang.annotation.*;

/**
 * {@link Component} 派生注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {
}
