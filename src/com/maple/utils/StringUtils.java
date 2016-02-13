package com.maple.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串相关的一些工具方法
 * Created by 余炳 on 16/2/5.
 */
public class StringUtils {

    /**
     * 判断字符串是不是为空
     * @param str 待判断的字符串
     * @return 如果为空，返回[true]，否则返回[false]
     */
    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是不是具有可见字符
     * @param str 待判断的字符串
     * @return 如果字符串没有可见字符，返回[false]，否则返回[true]
     */
    public static boolean hasText(String str){
        return str != null && str.trim().length() > 0;
    }

    /**
     * 字符串转[Integer],转换不成功返回默认值
     * @param value 待转换的值
     * @param def 默认值
     * @return 转换得到的值
     */
    public static Integer toInteger(String value, Integer def){
        try{
            value = value.trim();
            return Integer.valueOf(value);
        }
        catch (Exception e){
            return def;
        }
    }

    /**
     * 字符串转[Integer],转换不成功返回[0]
     * @param value 待转换的值
     * @return 转换得到的值
     */
    public static Integer toInteger(String value){
        return toInteger(value,0);
    }

    /**
     * 字符串转[Long]，转换不成功返回[def]的值
     * @param value 待转换的值
     * @param def 转换不成功返回的值
     * @return 转换返回的值
     */
    public static Long toLong(String value, Long def){
        try{
            value = value.trim();
            return Long.valueOf(value);
        }
        catch (Exception e){
            return def;
        }
    }

    /**
     * 字符串转[Long]，转换不成功返回[0]
     * @param value 待转换的值
     * @return 转换返回的值
     */
    public static Long toLong(String value){
        return toLong(value,0l);
    }

    /**
     * 字符串转[Double]，转换不成功就返回[def]的值
     * @param value 待转换的字符串
     * @param def 默认值
     * @return 转换得到的值
     */
    public static Double toDouble(String value, Double def){
        try{
            value = value.trim();
            return Double.valueOf(value);
        }
        catch (Exception e){
            return def;
        }
    }

    /**
     * 字符串转[Double]，转换不成功就返回[0]
     * @param value 待转换的字符串
     * @return 转换得到的值
     */
    public static Double toDouble(String value){
        return toDouble(value,0.0);
    }

    /**
     * 字符串转[Float]，转换不成功就返回[def]的值
     * @param value 待转换的值
     * @param def 默认值
     * @return 转换得到的值
     */
    public static Float toFloat(String value, Float def){
        try{
            value = value.trim();
            return Float.valueOf(value);
        }
        catch (Exception e){
            return def;
        }
    }

    /**
     * 字符串转[Float]，转换不成功就返回[0]
     * @param value 待转换的值
     * @return 转换得到的值
     */
    public static Float toFloat(String value){
        return toFloat(value,0.0f);
    }

    /**
     * 字符串转事件
     * @param value 待转换的字符串
     * @return 转换的时间
     */
    public static Date toDate(String value){
        if(!hasText(value)){
            return null;
        }
        value = value.trim();
        SimpleDateFormat format;

        format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        try{
            return format.parse(value);
        }
        catch (Exception e){
            format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                return format.parse(value);
            } catch (ParseException e1) {
                format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return format.parse(value);
                } catch (ParseException e2) {
                    return null;
                }
            }
        }
    }

    /**
     * 时间转字符串，只转换日志
     * @param date 待转换的日期
     * @return 转换得到的字符串
     */
    public static String formDateDay(Date date){
        if(date == null){
            return "";
        }
        return fromDate(date,"yyyy-MM-dd");
    }

    /**
     * 时间转字符串，转换到秒
     * @param date 待转换的时间
     * @return 转换得到的字符串
     */
    public static String formDateSecond(Date date){
        if(date == null){
            return "";
        }
        return fromDate(date,"yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 时间转字符串，转换到毫秒
     * @param date 待转换的时间
     * @return 转换得到的字符串
     */
    public static String formDateMilliSecond(Date date){
        if(date == null){
            return "";
        }
        return fromDate(date,"yyyy-MM-dd hh:mm:ss.SSS");
    }


    /**************** private method *****************/

    private static  String fromDate(Date date,String format){
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(date);
    }
}
