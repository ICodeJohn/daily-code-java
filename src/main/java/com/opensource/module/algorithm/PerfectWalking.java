package com.opensource.module.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/28 09:39
 * @Version V1.0
 */
public class PerfectWalking {
    public int balancedString(String ops) {
        int len = ops.length();
        int average = len / 4;
        char[] chars = ops.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            char ch = chars[i];
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        if(validate(map,average)){
            return 0;
        }
        int min = len;
        for (int l = 0,r = 0; l < len; l++) {
            while(r < len && !validate(map,average)){
                char ch1 = chars[r];
                map.put(ch1, map.get(ch1)-1);
                r++;
            }
            if(!validate(map,average)){
                break;
            }
            min = Math.min(min,r-l);
            char ch2 = chars[l];
            map.put(ch2,map.get(ch2)+1);
        }

        return min;
    }

    public static boolean validate(Map<Character, Integer> map,int average ) {
        for (Integer value : map.values()) {
            if (value > average) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }


}
