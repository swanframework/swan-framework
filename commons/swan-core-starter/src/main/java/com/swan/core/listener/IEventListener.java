package com.swan.core.listener;

public interface IEventListener<T> {

    public Class<T> eventType();

    public void invoke(T event);


}
