package com.opensource.module.huawei.od;
import java.util.*;

public class CompetitionWinner {

    static class Player {
        int id;
        long power;
        List<Player> beaten = new ArrayList<>(); // 记录该选手击败的所有对手

        Player(int id, long power) {
            this.id = id;
            this.power = power;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        // 读取输入并创建选手
        int id = 0;
        while (sc.hasNextLong()) {
            players.add(new Player(id++, sc.nextLong()));
        }
        sc.close();

        // 模拟比赛过程
        List<Player> currentRound = new ArrayList<>(players);

        while (currentRound.size() > 1) {
            List<Player> nextRound = new ArrayList<>();

            for (int i = 0; i < currentRound.size(); i += 2) {
                if (i + 1 < currentRound.size()) {
                    // 正常比赛
                    Player p1 = currentRound.get(i);
                    Player p2 = currentRound.get(i + 1);
                    Player winner = compare(p1, p2);
                    Player loser = (winner == p1) ? p2 : p1;

                    // 记录击败关系
                    winner.beaten.add(loser);
                    nextRound.add(winner);
                } else {
                    // 轮空，直接晋级
                    nextRound.add(currentRound.get(i));
                }
            }

            currentRound = nextRound;
        }

        // 冠军是最后剩下的选手
        Player champion = currentRound.get(0);

        // 亚军：从冠军击败的最后一个对手中找（决赛对手）
        // 因为冠军在决赛中击败的对手就是亚军
        Player runnerUp = champion.beaten.get(champion.beaten.size() - 1);

        // 季军：从冠军击败的所有对手中（不包括亚军）选出最强的
        Player thirdPlace = null;
        for (Player defeated : champion.beaten) {
            if (defeated != runnerUp) { // 排除亚军
                if (thirdPlace == null || compare(defeated, thirdPlace) == defeated) {
                    thirdPlace = defeated;
                }
            }
        }

        System.out.println(champion.id + " " + runnerUp.id + " " + thirdPlace.id);
    }

    // 比较两个选手，返回胜者
    // 规则：实力值大的获胜，实力值相等时id小的获胜
    private static Player compare(Player a, Player b) {
        if (a.power > b.power) {
            return a;
        } else if (a.power < b.power) {
            return b;
        } else {
            return a.id < b.id ? a : b;
        }
    }
}