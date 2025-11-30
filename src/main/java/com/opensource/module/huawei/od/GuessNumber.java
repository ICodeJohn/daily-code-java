package com.opensource.module.huawei.od;

import java.util.*;

public class GuessNumber {

    // 计算 XAYB：A = 数字且位置正确，B = 数字正确但位置不对
    public static int[] calcAB(String guess, String answer) {
        int A = 0;
        int B = 0;

        boolean[] usedA = new boolean[4];
        boolean[] usedG = new boolean[4];

        // 1. 先算 A
        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                A++;
                usedA[i] = true;
                usedG[i] = true;
            }
        }

        // 2. 再算 B（不能重复计数）
        for (int i = 0; i < 4; i++) {
            if (usedG[i]) continue;  // 已是 A 的跳过

            for (int j = 0; j < 4; j++) {
                if (usedA[j]) continue;  // 已经被匹配过

                if (guess.charAt(i) == answer.charAt(j)) {
                    B++;
                    usedA[j] = true;
                    break;
                }
            }
        }
        return new int[]{A, B};
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        String[] guess = new String[N];
        int[] A = new int[N];
        int[] B = new int[N];

        for (int i = 0; i < N; i++) {
            guess[i] = sc.next();
            String tip = sc.next();   // 例如 1A2B
            String[] parts = tip.split("A");
            A[i] = Integer.parseInt(parts[0]);
            B[i] = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
        }

        List<String> candidates = new ArrayList<>();

        // 枚举 0000 - 9999
        for (int num = 0; num < 10000; num++) {
            String ans = String.format("%04d", num);
            boolean ok = true;

            for (int i = 0; i < N; i++) {
                int[] ab = calcAB(guess[i], ans);
                if (ab[0] != A[i] || ab[1] != B[i]) {
                    ok = false;
                    break;
                }
            }

            if (ok) candidates.add(ans);
        }

        if (candidates.size() == 1) {
            System.out.println(candidates.get(0));
        } else {
            System.out.println("NA");
        }
    }
}
