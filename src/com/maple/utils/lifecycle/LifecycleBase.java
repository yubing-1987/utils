package com.maple.utils.lifecycle;

/**
 * 生命周期基础类
 * Created by 余炳 on 16/1/13.
 */
public abstract class LifecycleBase implements Lifecycle {


    /**
     * 生命周期支持类实例
     */
    private LifecycleSupport lifecycle = new LifecycleSupport(this);


    /**
     * 生命周期状态
     */
    private volatile LifecycleState state = LifecycleState.NEW;


    /**
     * 添加生命周期监听器
     */
    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycle.addLifecycleListener(listener);
    }


    /**
     * 获取全部的生命周期监听器
     */
    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return lifecycle.findLifecycleListeners();
    }


    /**
     * 删除生命周期监听器
     */
    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycle.removeLifecycleListener(listener);
    }


    /**
     * 执行生命周期事件
     */
    protected void fireLifecycleEvent(String type, Object data) {
        lifecycle.fireLifecycleEvent(type, data);
    }

    /**
     * 初始化
     */
    @Override
    public final synchronized void init() throws LifecycleException {
        if (!state.equals(LifecycleState.NEW)) {
            //只有状态为[NEW]的时候才能够初始化，其它情况都要抛出异常
            invalidTransition(Lifecycle.BEFORE_INIT_EVENT);
        }
        //设置状态为[INITIALIZING]
        setStateInternal(LifecycleState.INITIALIZING, null, false);

        try {
            //调用初始化接口
            initInternal();
        } catch (Throwable t) {
            setStateInternal(LifecycleState.FAILED, null, false);
            throw new LifecycleException("初始化失败！", t);
        }
        //设置[INITIALIZED]
        setStateInternal(LifecycleState.INITIALIZED, null, false);
    }

    /**
     * 执行初始化的接口
     */
    protected abstract void initInternal() throws LifecycleException;

    /**
     * 开始
     */
    @Override
    public final synchronized void start() throws LifecycleException {

        if (LifecycleState.STARTING_PREP.equals(state) ||
                LifecycleState.STARTING.equals(state) ||
                LifecycleState.STARTED.equals(state)) {
            //如果当前状态是[STARTING_PREP],[STARTING]或[STARTED]状态就直接返回

            return;
        }

        if (state.equals(LifecycleState.NEW)) {
            //如果是[NEW]状态，就需要先初始化
            init();
        } else if (state.equals(LifecycleState.FAILED)){
            //如果是[FAILED]就需要先停止
            stop();
        } else if (!state.equals(LifecycleState.INITIALIZED) &&
                !state.equals(LifecycleState.STOPPED)) {
            //如果是其它非[INITIALIZED]和[STOPPED]就需要报异常
            invalidTransition(Lifecycle.BEFORE_START_EVENT);
        }
        //设置状态为[STARTING_PREP]
        setStateInternal(LifecycleState.STARTING_PREP, null, false);

        setStateInternal(LifecycleState.STARTING, null, false);
        try {
            //执行开始接口
            startInternal();
        } catch (Throwable t) {
            setStateInternal(LifecycleState.FAILED, null, false);
            throw new LifecycleException("启动失败！", t);
        }

        if (state.equals(LifecycleState.FAILED) ||
                state.equals(LifecycleState.MUST_STOP)) {
            //如果是[FAILED]或[MUST_STOP]就需要停止
            stop();
        } else {
            if (!state.equals(LifecycleState.STARTING)) {
                //如果不是[STARTING]就表示有异常了
                invalidTransition(Lifecycle.AFTER_START_EVENT);
            }
            //设置状态为[STARTED]
            setStateInternal(LifecycleState.STARTED, null, false);
        }
    }


    /**
     * 执行开始的接口
     */
    protected abstract void startInternal() throws LifecycleException;


    /**
     * 停止
     */
    @Override
    public final synchronized void stop() throws LifecycleException {

        if (LifecycleState.STOPPING_PREP.equals(state) ||
                LifecycleState.STOPPING.equals(state) ||
                LifecycleState.STOPPED.equals(state)) {
            //如果状态是[STOPPING_PREP],[STOPPING]或[STOPPED]就直接返回
            //这表明已经调用过停止函数了
            return;
        }

        if (state.equals(LifecycleState.NEW)) {
            //对于一个刚刚新建的只需要把状态改一下就可以了
            state = LifecycleState.STOPPED;
            return;
        }

        /**
         * 只有处于已开始，错误，必须停止的状态才可以停止
         */
        if (!state.equals(LifecycleState.STARTED) &&
                !state.equals(LifecycleState.FAILED) &&
                !state.equals(LifecycleState.MUST_STOP)) {
            invalidTransition(Lifecycle.BEFORE_STOP_EVENT);
        }

        if (state.equals(LifecycleState.FAILED)) {
            // 如果当前状态是错误的就去执行消息
            fireLifecycleEvent(BEFORE_STOP_EVENT, null);
        } else {
            //其它清空需要设置状态为[STOPPING_PREP]
            setStateInternal(LifecycleState.STOPPING_PREP, null, false);
        }
        setStateInternal(LifecycleState.STOPPING, null, false);
        try {
            //调用停止接口
            stopInternal();
        } catch (Throwable t) {
            setStateInternal(LifecycleState.FAILED, null, false);
            throw new LifecycleException("停止失败！", t);
        }

        if (state.equals(LifecycleState.MUST_DESTROY)) {
            //如果状态为[MUST_DESTROY]就去销毁
            setStateInternal(LifecycleState.STOPPED, null, false);

            destroy();
        } else if (!state.equals(LifecycleState.FAILED)){
            // Shouldn't be necessary but acts as a check that sub-classes are
            // doing what they are supposed to.
            if (!state.equals(LifecycleState.STOPPING)) {
                //如果状态不是[STOPPING]就表示有地方错了，需要报异常
                invalidTransition(Lifecycle.AFTER_STOP_EVENT);
            }
            //设置状态为已停止
            setStateInternal(LifecycleState.STOPPED, null, false);
        }
    }


    /**
     * 执行停止操作的接口
     */
    protected abstract void stopInternal() throws LifecycleException;


    /**
     * 销毁
     */
    @Override
    public final synchronized void destroy() throws LifecycleException {
        if (LifecycleState.FAILED.equals(state)) {
            //如果当前状态是[FAILED]就需要先停止
            try {
                //停止
                stop();
            } catch (LifecycleException ignored) {
            }
        }

        if (LifecycleState.DESTROYING.equals(state) ||
                LifecycleState.DESTROYED.equals(state)) {
            //如果当前状态已经是[DESTROYING]或[DESTROYED]就可以直接返回了
            //防止多次销毁
            return;
        }

        //只有[STOPPED],[FAILED],[NEW],[INITIALIZED]状态才可以进行销毁操作
        if (!state.equals(LifecycleState.STOPPED) &&
                !state.equals(LifecycleState.FAILED) &&
                !state.equals(LifecycleState.NEW) &&
                !state.equals(LifecycleState.INITIALIZED)) {
            invalidTransition(Lifecycle.BEFORE_DESTROY_EVENT);
        }
        //设置状态为销毁中
        setStateInternal(LifecycleState.DESTROYING, null, false);

        try {
            //执行销毁函数
            destroyInternal();
        } catch (Throwable t) {
            setStateInternal(LifecycleState.FAILED, null, false);
            throw new LifecycleException("销毁失败！",t);
        }
        //设置已销毁状态
        setStateInternal(LifecycleState.DESTROYED, null, false);
    }

    /**
     * 销毁事件处理接口
     * @throws LifecycleException
     */
    protected abstract void destroyInternal() throws LifecycleException;

    /**
     * 获取状态
     */
    @Override
    public LifecycleState getState() {
        return state;
    }


    /**
     * 获取状态名称
     */
    @Override
    public String getStateName() {
        return getState().toString();
    }


    /**
     * 设置状态
     */
    protected synchronized void setState(LifecycleState state)
            throws LifecycleException {
        setStateInternal(state, null, true);
    }


    /**
     * 设置状态
     */
    protected synchronized void setState(LifecycleState state, Object data)
            throws LifecycleException {
        setStateInternal(state, data, true);
    }

    /**
     * 内部设置生命周期状态
     */
    private synchronized void setStateInternal(LifecycleState state,
                                               Object data, boolean check) throws LifecycleException {

        if (check) {
            //如果需要检查当前状态就进来这里
            if (state == null) {
                //竟然没有初始化，必须要异常
                invalidTransition("null");
                return;
            }

            //
            if (!(state == LifecycleState.FAILED ||
                    (this.state == LifecycleState.STARTING_PREP &&
                            state == LifecycleState.STARTING) ||
                    (this.state == LifecycleState.STOPPING_PREP &&
                            state == LifecycleState.STOPPING) ||
                    (this.state == LifecycleState.FAILED &&
                            state == LifecycleState.STOPPING))) {
                // 只有这些状态转换方向是被允许的
                // 任何状态转为[FAILED]
                // [STARTING_PREP]-->[STARTING]
                // [STOPPING_PREP]-->[STOPPING]
                // [FAILED]-->[STOPPING]
                invalidTransition(state.name());
            }
        }
        //修改状态
        this.state = state;
        String lifecycleEvent = state.getLifecycleEvent();
        //判断是否需要执行消息
        if (lifecycleEvent != null) {
            //执行消息
            fireLifecycleEvent(lifecycleEvent, data);
        }
    }

    //不被支持的状态转换
    private void invalidTransition(String type) throws LifecycleException {
        String msg = String.format("不支持的状态转换，[%s]-->[%s]",state.name(),type);
        throw new LifecycleException(msg);
    }
}

