package cn.xkw.springautocreate.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 徐康炜
 * @Date: 2020/9/4 17:29
 * @ProjectName: srbank
 * @Version 1.0
 * @Description: 日期工具类
 */
public class DateUtil {

    public static String getCurrenceTimeToString(String charset) {

        if ("".equals(charset)) {
            return charset;
        } else {
            return getGeneralCurrenceTimeToString(charset);
        }
    }

    public static String getGeneralCurrenceTimeToString(String charset) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(charset);
        String createdate = sdf.format(date);
        return createdate;
    }
}
