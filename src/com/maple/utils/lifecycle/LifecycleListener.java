package com.maple.utils.lifecycle;

/**
 * 生命周期事件处理接口
 * Created by 余炳 on 16/1/13.
 */
public interface LifecycleListener {
    /**
     * 执行生命周期消息
     */
    void lifecycleEvent(LifecycleEvent event);
}
