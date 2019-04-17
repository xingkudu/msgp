package com.hugo.midware.msgp.common.utils;

/**
 * @ClassName StringUtil
 * @Description string工具类
 * @Author hugo
 * @Date 2019-03-28 19:43
 * @Version 1.0
 **/
public final class StringUtil {

    public static String null2Empty(String str){
        return str == null ? "" : str;
    }

    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

}
