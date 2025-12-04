package com.opensource.module.huawei.od;
import java.util.*;

public class CompetitionWinner {

    static class Player {
        int id;
        long power;
        List<Player> beaten = new ArrayList<>();
        Player(int id, long power) {
            this.id = id;
            this.power = power;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        int id = 0;
        while (sc.hasNextLong()) {
            players.add(new Player(id++, sc.nextLong()));
        }
        sc.close();

        List<Player> round = players;

        // 模拟淘汰赛
        while (round.size() > 1) {
            List<Player> next = new ArrayList<>();
            for (int i = 0; i < round.size(); i += 2) {
                if (i + 1 < round.size()) {
                    Player a = round.get(i);
                    Player b = round.get(i + 1);
                    Player win = compare(a, b);
                    Player lose = (win == a ? b : a);
                    win.beaten.add(lose);
                    next.add(win);
                } else {
                    next.add(round.get(i)); // 轮空晋级
                }
            }
            round = next;
        }

        Player champion = round.get(0);
        Player runnerUp = champion.beaten.get(champion.beaten.size() - 1);

        // 收集季军候选
        Player third = null;

        // 冠军曾经击败的人（排除亚军）
        for (Player p : champion.beaten) {
            if (p != runnerUp) {
                if (third == null || compare(p, third) == p) {
                    third = p;
                }
            }
        }

        // 亚军曾经击败的人（决赛对手 champion 不在 beaten 里，无需排除）
        for (Player p : runnerUp.beaten) {
            if (third == null || compare(p, third) == p) {
                third = p;
            }
        }

        System.out.println(champion.id + " " + runnerUp.id + " " + third.id);
    }

    private static Player compare(Player a, Player b) {
        if (a.power != b.power)
            return a.power > b.power ? a : b;
        return a.id < b.id ? a : b;
    }
}