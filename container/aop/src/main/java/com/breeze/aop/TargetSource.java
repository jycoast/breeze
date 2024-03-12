package com.breeze.aop;

public interface TargetSource {

    Object getTarget();

    Class[] getTargetClass();
}
