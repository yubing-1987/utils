package com.maple.utils.log;

import java.util.logging.*;

/**
 * 自定义日志的实现类
 * Created by 余炳 on 16/1/17.
 */
public class LogImp implements Log {
    //日志实例
    private Logger logger;

    /**
     * 日志配置类
     */
    private static final String SIMPLE_FMT="java.util.logging.SimpleFormatter";

    static {
        try {
            Formatter fmt=(Formatter)Class.forName(SIMPLE_FMT).newInstance();
            Logger root=Logger.getLogger("");
            Handler handlers[]=root.getHandlers();
            for (Handler handler : handlers) {
                if (handler instanceof ConsoleHandler) {
                    handler.setFormatter(fmt);
                }
            }
        } catch( Throwable ignored) {
        }
    }

    public LogImp(String name ) {
        logger=Logger.getLogger(name);
    }

    @Override
    public final boolean isErrorEnabled() {
        return logger.isLoggable(Level.SEVERE);
    }

    @Override
    public final boolean isWarnEnabled() {
        return logger.isLoggable(Level.WARNING);
    }

    @Override
    public final boolean isInfoEnabled() {
        return logger.isLoggable(Level.INFO);
    }

    @Override
    public final boolean isDebugEnabled() {
        return logger.isLoggable(Level.FINE);
    }

    @Override
    public final boolean isFatalEnabled() {
        return logger.isLoggable(Level.SEVERE);
    }

    @Override
    public final boolean isTraceEnabled() {
        return logger.isLoggable(Level.FINER);
    }

    @Override
    public final void debug(Object message) {
        log(Level.FINE, String.valueOf(message), null);
    }

    @Override
    public final void debug(Object message, Throwable t) {
        log(Level.FINE, String.valueOf(message), t);
    }

    @Override
    public final void trace(Object message) {
        log(Level.FINER, String.valueOf(message), null);
    }

    @Override
    public final void trace(Object message, Throwable t) {
        log(Level.FINER, String.valueOf(message), t);
    }

    @Override
    public final void info(Object message) {
        log(Level.INFO, String.valueOf(message), null);
    }

    @Override
    public final void info(Object message, Throwable t) {
        log(Level.INFO, String.valueOf(message), t);
    }

    @Override
    public final void warn(Object message) {
        log(Level.WARNING, String.valueOf(message), null);
    }

    @Override
    public final void warn(Object message, Throwable t) {
        log(Level.WARNING, String.valueOf(message), t);
    }

    @Override
    public final void error(Object message) {
        log(Level.SEVERE, String.valueOf(message), null);
    }

    @Override
    public final void error(Object message, Throwable t) {
        log(Level.SEVERE, String.valueOf(message), t);
    }

    @Override
    public final void fatal(Object message) {
        log(Level.SEVERE, String.valueOf(message), null);
    }

    @Override
    public final void fatal(Object message, Throwable t) {
        log(Level.SEVERE, String.valueOf(message), t);
    }

    /**
     * 打印日志
     * @param level 打印日志的级别
     * @param msg 需要打印的信息
     * @param ex 异常信息
     */
    private void log(Level level, String msg, Throwable ex) {
        if (logger.isLoggable(level)) {
            // 获取堆栈信息
            Throwable dummyException=new Throwable();
            StackTraceElement locations[]=dummyException.getStackTrace();
            // 获取类名和方法名
            String cname = "unknown";
            String method = "unknown";
            if (locations != null && locations.length >2) {
                StackTraceElement caller = locations[2];
                cname = caller.getClassName();
                method = caller.getMethodName();
            }
            //打印信息
            if (ex==null) {
                logger.logp(level, cname, method, msg);
            } else {
                logger.logp(level, cname, method, msg, ex);
            }
        }
    }

    static Log getInstance(String name) {
        return new LogImp(name);
    }
}
