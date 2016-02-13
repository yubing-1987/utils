package com.maple.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * �ַ�����ص�һЩ���߷���
 * Created by ��� on 16/2/5.
 */
public class StringUtils {

    /**
     * �ж��ַ����ǲ���Ϊ��
     * @param str ���жϵ��ַ���
     * @return ���Ϊ�գ�����[true]�����򷵻�[false]
     */
    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    /**
     * �ж��ַ����ǲ��Ǿ��пɼ��ַ�
     * @param str ���жϵ��ַ���
     * @return ����ַ���û�пɼ��ַ�������[false]�����򷵻�[true]
     */
    public static boolean hasText(String str){
        return str != null && str.trim().length() > 0;
    }

    /**
     * �ַ���ת[Integer],ת�����ɹ�����Ĭ��ֵ
     * @param value ��ת����ֵ
     * @param def Ĭ��ֵ
     * @return ת���õ���ֵ
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
     * �ַ���ת[Integer],ת�����ɹ�����[0]
     * @param value ��ת����ֵ
     * @return ת���õ���ֵ
     */
    public static Integer toInteger(String value){
        return toInteger(value,0);
    }

    /**
     * �ַ���ת[Long]��ת�����ɹ�����[def]��ֵ
     * @param value ��ת����ֵ
     * @param def ת�����ɹ����ص�ֵ
     * @return ת�����ص�ֵ
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
     * �ַ���ת[Long]��ת�����ɹ�����[0]
     * @param value ��ת����ֵ
     * @return ת�����ص�ֵ
     */
    public static Long toLong(String value){
        return toLong(value,0l);
    }

    /**
     * �ַ���ת[Double]��ת�����ɹ��ͷ���[def]��ֵ
     * @param value ��ת�����ַ���
     * @param def Ĭ��ֵ
     * @return ת���õ���ֵ
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
     * �ַ���ת[Double]��ת�����ɹ��ͷ���[0]
     * @param value ��ת�����ַ���
     * @return ת���õ���ֵ
     */
    public static Double toDouble(String value){
        return toDouble(value,0.0);
    }

    /**
     * �ַ���ת[Float]��ת�����ɹ��ͷ���[def]��ֵ
     * @param value ��ת����ֵ
     * @param def Ĭ��ֵ
     * @return ת���õ���ֵ
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
     * �ַ���ת[Float]��ת�����ɹ��ͷ���[0]
     * @param value ��ת����ֵ
     * @return ת���õ���ֵ
     */
    public static Float toFloat(String value){
        return toFloat(value,0.0f);
    }

    /**
     * �ַ���ת�¼�
     * @param value ��ת�����ַ���
     * @return ת����ʱ��
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
     * ʱ��ת�ַ�����ֻת����־
     * @param date ��ת��������
     * @return ת���õ����ַ���
     */
    public static String formDateDay(Date date){
        if(date == null){
            return "";
        }
        return fromDate(date,"yyyy-MM-dd");
    }

    /**
     * ʱ��ת�ַ�����ת������
     * @param date ��ת����ʱ��
     * @return ת���õ����ַ���
     */
    public static String formDateSecond(Date date){
        if(date == null){
            return "";
        }
        return fromDate(date,"yyyy-MM-dd hh:mm:ss");
    }

    /**
     * ʱ��ת�ַ�����ת��������
     * @param date ��ת����ʱ��
     * @return ת���õ����ַ���
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