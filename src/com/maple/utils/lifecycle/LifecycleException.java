package com.maple.utils.lifecycle;

/**
 * 生命周期异常
 * Created by 余炳 on 16/1/13.
 */
public class LifecycleException extends Exception {

    /**
     * 构造函数，新实例一个异常
     */
    public LifecycleException() {
        super();
    }


    /**
     * 构造函数，新实例一个异常，并传入消息
     */
    public LifecycleException(String message) {
        super(message);
    }


    /**
     * 构造函数，拷贝一个异常
     */
    public LifecycleException(Throwable throwable) {
        super(throwable);
    }


    /**
     * 构造函数，拷贝异常，并传入消息
     */
    public LifecycleException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
