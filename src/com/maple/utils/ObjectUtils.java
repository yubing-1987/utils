package com.maple.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Object相关的一些工具类
 * Created by 余炳 on 16/2/5.
 */
public class ObjectUtils {

    /**
     * 从类中获取指定名称的[Field]
     * @param c 需要查询的类
     * @param name [Field]名称
     * @return 查找到的[Field]实例
     */
    public static Field getField(Class c,String name){
        if(c == null || !StringUtils.hasText(name)){
            return null;
        }
        try {
            return c.getDeclaredField(name);
        } catch (NoSuchFieldException ignored) {
        }

        return getField(c.getSuperclass(),name);
    }

    /**
     * 获取指定名称的方法
     * @param c 类
     * @param name 方法名称
     * @return 获取到的方法实例
     */
    @SuppressWarnings("unchecked")
    public static Method getMethod(Class c,String name){
        if(c == null || !StringUtils.hasText(name)){
            return null;
        }

        try {
            return c.getDeclaredMethod(name);
        } catch (NoSuchMethodException ignored) {
        }

        return getMethod(c.getSuperclass(), name);
    }

    /**
     * 获取全部的[Field]，排除[volatile],[static]和[final]的
     * @param c 类
     * @return 获取到的Field列表
     */
    public static List<Field> getAllField(Class c){
        List<Field> list = new ArrayList<>();

        if(c == null){
            return list;
        }

        for(Field f:c.getDeclaredFields()){
            if(!Modifier.isVolatile(f.getModifiers())
                    && !Modifier.isStatic(f.getModifiers())
                    && !Modifier.isFinal(f.getModifiers())){
                list.add(f);
            }
        }

        list.addAll(getAllField(c.getSuperclass()));

        return list;
    }
}
