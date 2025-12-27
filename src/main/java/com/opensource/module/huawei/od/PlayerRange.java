package com.opensource.module.huawei.od;

import java.util.*;

public class PlayerRange {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // 读取第一行
            String firstLine = scanner.nextLine().trim();
            String[] firstParts = firstLine.split(",");

            if (firstParts.length != 2) {
                System.out.println(-1);
                return;
            }

            int M = Integer.parseInt(firstParts[0]);
            int N = Integer.parseInt(firstParts[1]);

            // 验证M和N范围
            if (M < 3 || M > 10 || N < 3 || N > 100) {
                System.out.println(-1);
                return;
            }

            int[][] scores = new int[M][N];

            // 读取评委打分
            for (int i = 0; i < M; i++) {
                String line = scanner.nextLine().trim();
                String[] parts = line.split(",");

                // 验证每行分数数量
                if (parts.length != N) {
                    System.out.println(-1);
                    return;
                }

                for (int j = 0; j < N; j++) {
                    int score = Integer.parseInt(parts[j]);
                    // 验证分数范围
                    if (score < 1 || score > 10) {
                        System.out.println(-1);
                        return;
                    }
                    scores[i][j] = score;
                }
            }

            // 处理选手数据
            List<Player> players = new ArrayList<>();
            for (int playerId = 0; playerId < N; playerId++) {
                int totalScore = 0;
                int[] scoreCounts = new int[11]; // 索引1-10存储1-10分的数量

                for (int judge = 0; judge < M; judge++) {
                    int score = scores[judge][playerId];
                    totalScore += score;
                    scoreCounts[score]++;
                }

                players.add(new Player(playerId + 1, totalScore, scoreCounts));
            }

            // 排序
            Collections.sort(players, new PlayerComparator());
            players.sort(new PlayerComparator());

            // 输出前3名
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                if (i > 0) result.append(",");
                result.append(players.get(i).id);
            }
            System.out.println(result.toString());

        } catch (Exception e) {
            System.out.println(-1);
        } finally {
            scanner.close();
        }
    }

    // 选手类
    static class Player {
        int id;          // 选手编号
        int totalScore;  // 总分
        int[] scoreCounts; // 各分值数量，索引1-10对应1-10分

        public Player(int id, int totalScore, int[] scoreCounts) {
            this.id = id;
            this.totalScore = totalScore;
            this.scoreCounts = scoreCounts;
        }
    }

    // 比较器
    static class PlayerComparator implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {
            // 先比较总分（降序）
            if (p1.totalScore != p2.totalScore) {
                return Integer.compare(p2.totalScore, p1.totalScore);
            }

            // 总分相同，从10分到1分依次比较各分值数量
            for (int score = 10; score >= 1; score--) {
                int count1 = p1.scoreCounts[score];
                int count2 = p2.scoreCounts[score];
                if (count1 != count2) {
                    return Integer.compare(count2, count1);
                }
            }

            return 0;
        }
    }
}
