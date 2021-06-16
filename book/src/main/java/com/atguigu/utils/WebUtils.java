package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @title: webUtils
 * @Author ChenShu
 * @Date: 2021/6/4 21:41
 * @Version 1.0
 */
public class WebUtils {
    public static Object copyParamToBean(Map value,Object bean){
        try {
            System.out.println("注入之前：" + bean);
/**
 * 把所有请求的参数都注入到 user 对象中
 */

            BeanUtils.populate(bean,value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
