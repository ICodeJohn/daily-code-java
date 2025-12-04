package com.opensource.module.huawei.od;

import java.util.*;

public class LampCompare_001 {

    public static class Lamp {
        int idx, x1, x2, y1, y2;

        public Lamp(int idx, int x1, int x2, int y1, int y2) {
            this.idx = idx;
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nums = scanner.nextInt();
        List<Lamp> lampList = new ArrayList<>();
        PriorityQueue<Lamp> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.y1));
        for (int i = 0; i < nums; i++) {
            int idx = scanner.nextInt();
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            Lamp lamp = new Lamp(idx, x1, x2, y1, y2);
            lampList.add(lamp);
            pq.offer(lamp);
        }

        Set<Integer> processed = new HashSet<>();
        StringBuilder result = new StringBuilder();

        while (true) {
            Lamp baseLamp = null;
            while (!pq.isEmpty()) {
                Lamp temp = pq.poll();
                if (!processed.contains(temp.idx)) {
                    baseLamp = temp;
                    break;
                }
            }

            if (baseLamp == null) {
                break;
            }

            List<Lamp> sameRow = new ArrayList<>();
            int height = baseLamp.y2 - baseLamp.y1;
            for (Lamp lamp : lampList) {
                if (!processed.contains(lamp.idx) && Math.abs(lamp.y1 - baseLamp.y1) <= height / 2) {
                    sameRow.add(lamp);
                }
            }

            sameRow.sort(Comparator.comparingInt(a -> a.x1));
            for (Lamp lamp : sameRow) {
                int idx = lamp.idx;
                if (result.isEmpty()) {
                    result.append(idx);
                } else {
                    result.append(" ");
                    result.append(idx);
                }
                processed.add(idx);
            }
        }
        System.out.println(result);
        scanner.close();
    }
}
