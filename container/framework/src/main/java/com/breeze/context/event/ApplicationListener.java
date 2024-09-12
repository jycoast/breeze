package com.breeze.context.event;

public interface ApplicationListener<E> {

    void onApplicationEvent(E event);
}
