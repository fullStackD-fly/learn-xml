package cn.thf.sorm.utils;

import java.lang.reflect.Method;

/**
 * @author tianhf
 * @date 2020/5/23 16:58
 * @Version 1.0
 */
public class ReflectUtils {
    public static  Object  invokeGet(String fieldname,Object obj){
        try {
            Method m= obj.getClass().getMethod("get"+ StringUtils.firstChar2UpperCase(fieldname),null);
            return  m.invoke(obj,null);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }

    }
    public static  void  invokeSet(Object obj,String fieldname,Object fieldValue){
        try {
            Method m= obj.getClass().getMethod("set"+ StringUtils.firstChar2UpperCase(fieldname),fieldValue.getClass());
            m.invoke(obj,fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
