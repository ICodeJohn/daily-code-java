package com.opensource.module.huawei.od;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrStr_051 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().trim();
        String regex = sc.nextLine().trim();
        sc.close();
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(str);
        if(matcher.find()){
            System.out.println(matcher.start());
        }else {
            System.out.println("-1");
        }
    }

}
