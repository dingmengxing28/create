package cn.xkw.springautocreate.utils;

/**
 * @Author: 徐康炜
 * @Date: 2020/9/4 17:29
 * @ProjectName: srbank
 * @Version 1.0
 * @Description: 字符串工具类
 */
public class StringUtils {

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
