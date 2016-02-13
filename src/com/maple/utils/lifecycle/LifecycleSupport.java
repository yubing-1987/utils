package com.maple.utils.lifecycle;

/**
 * 生命周期支持类，主要是事件分发，监听器的管理
 * Created by 余炳 on 16/1/13.
 */
public class LifecycleSupport {
    /**
     * 构造函数
     */
    public LifecycleSupport(Lifecycle lifecycle) {

        super();
        this.lifecycle = lifecycle;

    }

    /**
     * 生命周期实例
     */
    private Lifecycle lifecycle = null;


    /**
     * 监听器
     */
    private LifecycleListener listeners[] = new LifecycleListener[0];

    /**
     * 线程同步锁实例
     */
    private final Object listenersLock = new Object(); // Lock object for changes to listeners


    /**
     * 添加一个生命周期监听器，不会去重，如果添加了多次，就会被存储多次
     * 调用的时候也会调用多次
     */
    public void addLifecycleListener(LifecycleListener listener) {

        synchronized (listenersLock) {
            LifecycleListener results[] =
                    new LifecycleListener[listeners.length + 1];
            //拷贝原来的监听器数组
            System.arraycopy(listeners, 0, results, 0, listeners.length);
            //添加上新的
            results[listeners.length] = listener;
            listeners = results;
        }

    }


    /**
     * 获取全部的监听器
     */
    public LifecycleListener[] findLifecycleListeners() {

        return listeners;

    }


    /**
     * 执行事件
     */
    public void fireLifecycleEvent(String type, Object data) {
        //根据事件类型和数据新建一个事件实例
        LifecycleEvent event = new LifecycleEvent(lifecycle, type, data);
        //遍历监听器并执行
        LifecycleListener interested[] = listeners;
        for (LifecycleListener anInterested : interested){
            anInterested.lifecycleEvent(event);
        }

    }


    /**
     * 删除一个监听器，找到第一个一致的就去删除
     */
    public void removeLifecycleListener(LifecycleListener listener) {

        synchronized (listenersLock) {
            //查找第一个一致的监听器位置
            int n = -1;
            for (int i = 0; i < listeners.length; i++) {
                if (listeners[i] == listener) {
                    n = i;
                    break;
                }
            }
            //判断是否找到了
            if (n < 0)
                return;
            //
            LifecycleListener results[] =
                    new LifecycleListener[listeners.length - 1];
            //拷贝
            int j = 0;
            for (int i = 0; i < listeners.length; i++) {
                if (i != n)
                    results[j++] = listeners[i];
            }
            listeners = results;
        }

    }
}
