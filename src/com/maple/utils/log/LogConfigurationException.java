package com.maple.utils.log;

/**
 * 日志配置的时候的异常
 * Created by 余炳 on 16/1/17.
 */
public class LogConfigurationException extends RuntimeException {

    /**
     * 构造函数
     */
    public LogConfigurationException() {
        super();
    }

    /**
     * 构造函数
     * @param message 异常信息
     */
    public LogConfigurationException(String message) {
        super(message);
    }

    /**
     * 构造函数
     * @param cause 堆栈信息
     */
    public LogConfigurationException(Throwable cause) {
        this( ((cause == null) ? null : cause.toString()), cause);
    }

    /**
     * 构造函数
     * @param message 异常信息
     * @param cause 堆栈信息
     */
    public LogConfigurationException(String message, Throwable cause) {

        super(message);
        this.cause = cause;

    }

    /**
     * 堆栈信息
     */
    protected Throwable cause = null;

    /**
     * 获取异常信息
     */
    @Override
    public Throwable getCause() {

        return (this.cause);

    }

}
