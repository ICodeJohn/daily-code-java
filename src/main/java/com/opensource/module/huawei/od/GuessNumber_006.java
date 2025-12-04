package com.opensource.module.huawei.od;

import java.util.*;

public class

GuessNumber_006 {

    public static int[] calcAB(String guess, String answer) {
        int A = 0;
        int B = 0;

        boolean[] usedGuess = new boolean[4];
        boolean[] usedAnswer = new boolean[4];

        // 先计算 A
        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                A++;
                usedGuess[i] = true;
                usedAnswer[i] = true;
            }
        }

        // 再计算 B
        for (int i = 0; i < 4; i++) {
            if (usedGuess[i]) continue;   // 已用于 A 的不再用于 B

            for (int j = 0; j < 4; j++) {
                if (usedAnswer[j]) continue;

                if (guess.charAt(i) == answer.charAt(j)) {
                    B++;
                    usedGuess[i] = true;
                    usedAnswer[j] = true;
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
