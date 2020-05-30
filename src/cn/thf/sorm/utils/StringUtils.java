package cn.thf.sorm.utils;

/**
 * @author tianhf
 * @date 2020/5/23 16:58
 * @Version 1.0
 */
public class StringUtils {
    /**
     * 首字母转大写
     * @param str
     * @return
     */
    public static String firstChar2UpperCase(String str){
        return str.toUpperCase().substring(0,1)+str.substring(1);
    }
}
