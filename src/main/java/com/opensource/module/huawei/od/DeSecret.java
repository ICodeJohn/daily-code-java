package com.opensource.module.huawei.od;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DeSecret {

//    public static void main(String[] args) {
//
//        Map<Integer,Character> map = new HashMap<Integer,Character>();
//        int i = 1;
//        for(char ch = 'a'; ch <='z'; ch++){
//            map.put(i,ch);
//            i++;
//        }
//        Scanner sc = new Scanner(System.in);
//        String[] nums = sc.nextLine().trim().split("\\*");
//        StringBuilder builder =new StringBuilder();
//        for (String num : nums) {
//            if(num.isEmpty()){
//                continue;
//            }
//            builder.append(map.get(Integer.parseInt(num)));
//        }
//        System.out.println(builder);
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().trim().split("\\*");

        StringBuilder sb = new StringBuilder();
        for (String a : arr) {
            if (a.isEmpty()) continue;

            int num = Integer.parseInt(a);
            if (num >= 1 && num <= 26) {
                sb.append((char)('a' + num - 1));
            }
        }
        System.out.println(sb);
    }
}
