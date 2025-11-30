package com.opensource.module.huawei.od;

import java.util.*;

public class MyDeque {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        List<Integer> list = new ArrayList<>();
        int nextRemove = 1;
        int adjustCount = 0;

        for (int i = 0; i < 2 * n; i++) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");

            if (parts[0].equals("head")) {
                list.add(0, Integer.parseInt(parts[2]));
            } else if (parts[0].equals("tail")) {
                list.add(Integer.parseInt(parts[2]));
            } else { // remove
                if (list.isEmpty()) continue;

                if (list.get(0) == nextRemove) {
                    list.remove(0);
                } else {
                    adjustCount++;
                    Collections.sort(list);  // 这才是真正的"调整" - 重新排序
                    list.remove(0);
                }
                nextRemove++;
            }
        }

        System.out.println(adjustCount);
        scanner.close();
    }
}
