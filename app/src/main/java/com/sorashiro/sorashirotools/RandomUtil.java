package com.sorashiro.sorashirotools;

/**
 * @author Sora
 * @date 2016/11/6
 *
 * Random util class.
 * 跟随机相关的工具类。
 *
 */

public class RandomUtil {

    /**
     * Generate N non redundant numbers in a range randomly.
     * 随机生成N个在指定范围内不重复的数。
     */
    public static int[] RandomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }

}
