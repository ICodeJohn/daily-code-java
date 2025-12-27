package com.opensource.module.huawei.od2;

import java.util.Scanner;

//https://hydro.ac/d/coder_gather/record/694e32905c72468cd688a7db
public class PersonnelDispatch {

    static long x, y, cntx, cnty;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        x = sc.nextLong();
        y = sc.nextLong();
        cntx = sc.nextLong();
        cnty = sc.nextLong();
        long left = 1;
        long right = (cntx + cnty) * Math.max(x, y);
        long ans = 0;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (check(mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }

    static boolean check(long k) {
        long xy = x * y;
        long onlyX = k / y - k / xy;
        long onlyY = k / x - k / xy;
        long both = k - k / y - k / x + k / xy;
        long needX = Math.max(0, cntx - onlyX);
        long needY = Math.max(0, cnty - onlyY);
        return needX + needY <= both;
    }
}
