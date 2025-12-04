package com.opensource.module.huawei.od;

import java.util.*;

public class CircuitError_012_A {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String S = sc.next();
        String T = sc.next();

        long A = 0; // S=0, T=0
        long B = 0; // S=1, T=0
        long ones = 0;  // count of S=1
        long zeros = 0; // count of S=0

        for (int i = 0; i < N; i++) {
            char s = S.charAt(i);
            char t = T.charAt(i);

            if (s == '1') ones++;
            else zeros++;

            if (s == '0' && t == '0') A++;
            if (s == '1' && t == '0') B++;
        }

        long result = A * ones + B * zeros - A * B;

        System.out.println(result);
    }
}
