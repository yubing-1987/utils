package com.maple.utils.log;

/**
 * 自定义日志接口
 * Created by 余炳 on 16/1/17.
 */
public interface Log {
    /**
     * 是否启用打印调试日志
     */
    boolean isDebugEnabled();

    /**
     * 是否启用打印错误日志
     */
    boolean isErrorEnabled();

    /**
     * 使用启用打印致命日志
     */
    boolean isFatalEnabled();

    /**
     * 是否启用打印信息日志
     */
    boolean isInfoEnabled();

    /**
     * 是否启用打印轨迹日志
     */
    boolean isTraceEnabled();

    /**
     * 是否启用打印告警日志
     */
    boolean isWarnEnabled();

    /**
     * 打印轨迹日志
     * @param message 需要打印的信息
     */
    void trace(Object message);

    /**
     * 打印轨迹日志
     * @param message 需要打印的信息
     * @param t 异常信息
     */
    void trace(Object message, Throwable t);

    /**
     * 打印调试信息
     * @param message 需要打印的信息
     */
    void debug(Object message);

    /**
     * 打印调试信息
     * @param message 需要打印的信息
     * @param t 异常信息
     */
    void debug(Object message, Throwable t);

    /**
     * 打印信息日志
     * @param message 需要打印的信息
     */
    void info(Object message);

    /**
     * 打印信息日志
     * @param message 需要打印的信息
     * @param t 异常信息
     */
    void info(Object message, Throwable t);

    /**
     * 打印告警信息
     * @param message 需要打印的信息
     */
    void warn(Object message);

    /**
     * 打印告警信息
     * @param message 需要打印的信息
     * @param t 异常信息
     */
    void warn(Object message, Throwable t);

    /**
     * 打印错误信息
     * @param message 需要打印的信息
     */
    void error(Object message);

    /**
     * 打印错误信息
     * @param message 需要打印的信息
     * @param t 异常信息
     */
    void error(Object message, Throwable t);

    /**
     * 打印致命信息
     * @param message 需要打印的信息
     */
    void fatal(Object message);

    /**
     * 打印致命信息
     * @param message 需要打印的信息
     * @param t 异常信息
     */
    void fatal(Object message, Throwable t);
}