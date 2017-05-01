package com.sorashiro.sorashirotools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sora
 * @date 2016.7.25
 *
 * Regex util class.
 * 正则表达式工具类。
 *
 */

public class RegexUtil {

    /**
     * 判断手机号码是否合法
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles){

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();

    }

    /**
     * 判断邮编是否合法
     * @param zipString
     * @return
     */
    public static boolean isZip(String zipString){
        String str = "^[1-9][0-9]{5}$";
        return Pattern.compile(str).matcher(zipString).matches();
    }

    /**
     * 判断邮箱是否合法
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
