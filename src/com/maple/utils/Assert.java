package com.maple.utils;

/**
 * 各种断言
 * Created by 余炳 on 16/2/5.
 */
public class Assert {

    /**
     * 断言为[null]
     * @param obj 被断言的object
     */
    public static void isNull(Object obj){
        if(obj != null){
            throw new RuntimeException("[Assert Error]--The object must be null!");
        }
    }

    /**
     * 断言不为[null]
     * @param obj 被断言的object
     */
    public static void notNull(Object obj){
        if(obj == null){
            throw new RuntimeException("[Assert Error]--The object can not be null!");
        }
    }

    /**
     * 断言字符串为空
     * @param str 被断言的字符串
     */
    public static void isEmpty(String str){
        if(!StringUtils.isEmpty(str)){
            throw new RuntimeException("[Assert Error]--The string must empty!");
        }
    }

    /**
     * 断言字符串不为空
     * @param str 被断言的字符串
     */
    public static void notEmpty(String str){
        if(StringUtils.isEmpty(str)){
            throw new RuntimeException("[Assert Error]--The string can not be empty!");
        }
    }

    /**
     * 断言具有可见字符
     * @param str 被断言的字符串
     */
    public static void hasText(String str){
        if(!StringUtils.hasText(str)){
            throw new RuntimeException("[Assert Error]--The string must has text!");
        }
    }

    /**
     * 断言为真
     * @param b 被断言的值
     */
    public static void isTrue(boolean b){
        if(!b){
            throw new RuntimeException("[Assert Error]--Must true!");
        }
    }

    /**
     * 断言为假
     * @param b 被断言的值
     */
    public static void isFalse(boolean b){
        if(b){
            throw new RuntimeException("[Assert Error]--Must false!");
        }
    }

    /**
     * 断言是指定类的实例
     * @param o 断言的实例
     * @param c 指定的类
     */
    public static void isInstance(Object o,Class c){
        notNull(o);
        notNull(c);
        if(!c.isInstance(o)){
            throw new RuntimeException("[Assert Error]--The object must instance from ["+c.getName()+"]!");
        }
    }
}
