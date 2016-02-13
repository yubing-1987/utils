package com.maple.utils.lifecycle;

/**
 * 生命周期状态
 * Created by 余炳 on 16/1/13.
 */
public enum LifecycleState {
    //新实例化后的状态
    NEW(false, null),
    //正在出事
    INITIALIZING(false, Lifecycle.BEFORE_INIT_EVENT),
    //完成初始化
    INITIALIZED(false, Lifecycle.AFTER_INIT_EVENT),
    //启动前
    STARTING_PREP(false, Lifecycle.BEFORE_START_EVENT),
    //启动中
    STARTING(true, Lifecycle.START_EVENT),
    //启动后
    STARTED(true, Lifecycle.AFTER_START_EVENT),
    //停止前
    STOPPING_PREP(true, Lifecycle.BEFORE_STOP_EVENT),
    //停止中
    STOPPING(false, Lifecycle.STOP_EVENT),
    //停止后
    STOPPED(false, Lifecycle.AFTER_STOP_EVENT),
    //销毁中
    DESTROYING(false, Lifecycle.BEFORE_DESTROY_EVENT),
    //销毁后
    DESTROYED(false, Lifecycle.AFTER_DESTROY_EVENT),
    //错误
    FAILED(false, null),
    //必须停止
    MUST_STOP(true, null),
    //必须销毁
    MUST_DESTROY(false, null);

    private final boolean available;
    private final String lifecycleEvent;

    LifecycleState(boolean available, String lifecycleEvent) {
        this.available = available;
        this.lifecycleEvent = lifecycleEvent;
    }

    /**
     *
     * 判断是否可用
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * 获取生命周期消息名称
     */
    public String getLifecycleEvent() {
        return lifecycleEvent;
    }
}

