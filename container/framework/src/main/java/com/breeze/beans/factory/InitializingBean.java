package com.breeze.beans.factory;

/**
 * 初始化Bean回调
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
