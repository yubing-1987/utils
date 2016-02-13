package com.maple.utils.log;

import java.util.Properties;

/**
 * 日志工厂，用于创建日志实例
 * Created by 余炳 on 16/1/17.
 */
public class LogFactory {

    /**
     * 日志工厂的唯一实例
     */
    private static LogFactory singleton=new LogFactory();

    /**
     * 日志实例的配置信息
     */
    Properties logConfig;

    /**
     * 构造函数，配置信息为空
     */
    private LogFactory() {
        logConfig=new Properties();
    }

    /**
     * 设置配置信息
     * @param p 新的配置信息
     */
    void setLogConfig( Properties p ) {
        this.logConfig=p;
    }

    /**
     * 获取指定名称的日志
     * @param name 日志名称
     * @return 得到的日志实例
     * @throws LogConfigurationException 日志配置异常
     */
    public Log getInstance(String name)
            throws LogConfigurationException {
        return LogImp.getInstance(name);
    }

    /**
     * 获取配置属性的值
     * @param name 属性名称
     * @return 属性值
     */
    public Object getAttribute(String name) {
        return logConfig.get(name);
    }


    /**
     * 获取全部的属性名称
     * @return 全部属性名称
     */
    public String[] getAttributeNames() {
        String result[] = new String[logConfig.size()];
        return logConfig.keySet().toArray(result);
    }

    /**
     * 删除某个属性
     * @param name 属性名称
     */
    public void removeAttribute(String name) {
        logConfig.remove(name);
    }


    /**
     * 设置某个属性
     * @param name 属性名称
     * @param value 属性值
     */
    public void setAttribute(String name, Object value) {
        logConfig.put(name, value);
    }


    /**
     * 通过类名获取日志实例
     * @param clazz 类名称
     * @return 日志实例
     * @throws LogConfigurationException 配置异常
     */
    public Log getInstance(Class<?> clazz)
            throws LogConfigurationException {
        return getInstance( clazz.getName());
    }

    /**
     * 获取日志工厂的唯一实例
     * @return 日志工厂
     * @throws LogConfigurationException 配置异常
     */
    public static LogFactory getFactory() throws LogConfigurationException {
        return singleton;
    }


    /**
     * 获取是在实例
     * @param clazz 类名称
     * @return 日志实例
     * @throws LogConfigurationException 配置异常
     */
    public static Log getLog(Class<?> clazz)
            throws LogConfigurationException {
        return (getFactory().getInstance(clazz));

    }


    /**
     * 获取日志实例
     * @param name 日志名称
     * @return 日志实例
     * @throws LogConfigurationException 配置异常
     */
    public static Log getLog(String name)
            throws LogConfigurationException {
        return (getFactory().getInstance(name));

    }
}
