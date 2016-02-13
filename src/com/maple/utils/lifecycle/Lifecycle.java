package com.maple.utils.lifecycle;

/**
 * 生命周期接口
 * Created by 余炳 on 16/1/13.
 */
public interface Lifecycle {

    /**
     * 初始化之前的状态
     */
    String BEFORE_INIT_EVENT = "before_init";


    /**
     * 初始化之后的状态
     */
    String AFTER_INIT_EVENT = "after_init";


    /**
     * 开始事件
     */
    String START_EVENT = "start";


    /**
     * 开始前的状态
     */
    String BEFORE_START_EVENT = "before_start";


    /**
     * 开始之后的状态
     */
    String AFTER_START_EVENT = "after_start";


    /**
     * 停止事件
     */
    String STOP_EVENT = "stop";


    /**
     * 停止之前的状态
     */
    String BEFORE_STOP_EVENT = "before_stop";


    /**
     * 停止之后的状态
     */
    String AFTER_STOP_EVENT = "after_stop";


    /**
     * 销毁之后的状态
     */
    String AFTER_DESTROY_EVENT = "after_destroy";


    /**
     * 销毁之前的状态
     */
    String BEFORE_DESTROY_EVENT = "before_destroy";


    /**
     * 周期性的事件
     */
    String PERIODIC_EVENT = "periodic";


    /**
     * 通过配置文件启动的时候发出的事件
     */
    String CONFIGURE_START_EVENT = "configure_start";


    /**
     * 通过配置文件停止的时候发出的事件
     */
    String CONFIGURE_STOP_EVENT = "configure_stop";


    // --------------------------------------------------------- Public Methods


    /**
     * 添加生命周期监听器
     */
    void addLifecycleListener(LifecycleListener listener);


    /**
     * 获取全部的生命周期监听器
     */
    LifecycleListener[] findLifecycleListeners();


    /**
     * 删除生命周期监听器
     */
    void removeLifecycleListener(LifecycleListener listener);


    /**
     * 初始化
     */
    void init() throws LifecycleException;

    /**
     * 开始
     */
    void start() throws LifecycleException;


    /**
     * 停止
     */
    void stop() throws LifecycleException;

    /**
     * 销毁
     */
    void destroy() throws LifecycleException;


    /**
     * 获取状态
     */
    LifecycleState getState();


    /**
     * 获取状态的名称
     */
    String getStateName();
}
