package com.maple.utils.lifecycle;

import java.util.EventObject;

/**
 * 生命周期事件
 * Created by 余炳 on 16/1/13.
 */
public class LifecycleEvent extends EventObject {

    /**
     * 构造函数
     * @param lifecycle 生命周期实例
     * @param type 事件类型
     * @param data 相关的数据
     */
    public LifecycleEvent(Lifecycle lifecycle, String type, Object data) {

        super(lifecycle);
        this.type = type;
        this.data = data;
    }

    /**
     * 事件需要的数据
     */
    private Object data = null;


    /**
     * 事件的类型
     */
    private String type = null;

    /**
     * 获取事件数据
     */
    public Object getData() {

        return (this.data);

    }

    /**
     * 获取生命周期
     */
    public Lifecycle getLifecycle() {

        return (Lifecycle) getSource();

    }

    /**
     * 获取事件的类型
     */
    public String getType() {

        return (this.type);

    }
}
