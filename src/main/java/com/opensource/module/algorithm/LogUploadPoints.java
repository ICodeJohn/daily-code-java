package com.opensource.module.algorithm;

import java.util.Scanner;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/28 19:53
 * @Version V1.0
 */
public class LogUploadPoints {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            int res = solution(line);
            System.out.println(res);
        }
    }

    private static int solution(String line) {
        int score = 0;
        String[] split = line.split(" ");
        int[] counts = new int[split.length];


        for (int i = 0; i < counts.length; i++) {
            counts[i] = Integer.parseInt(split[i]);
        }

        if (counts.length > 0 && counts[0] >= 100) {
            return 100;
        }

        for (int i = 0; i < counts.length; i++) {
            score = Math.max(score, getScore(counts, i));
        }
        return score;
    }

    private static int getScore(int[] counts, int ts) {
        int score = 0;
        int total = 0;
        for (int i = 0; i <= ts; i++) {
            if (total + counts[i] < 100) {
                score += counts[i];
                score -= counts[i] * (ts - i);
                total += counts[i];
            } else {
                int diff = (total + counts[i]) - 100;
                score += counts[i] -diff;
                break;
            }
        }
        return score;
    }
}
