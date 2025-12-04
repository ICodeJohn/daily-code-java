package com.opensource.module.huawei.od;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class ApplyVLan_003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String vlans = sc.nextLine().trim();
        int target = Integer.parseInt(sc.nextLine().trim());
        TreeSet<Integer> treeSet = new TreeSet<>();

        String[] partitions = vlans.split(",");
        for (String partition : partitions) {
            if (partition.contains("-")) {
                String[] parts = partition.split("-");
                for (int i = Integer.parseInt(parts[0]); i <= Integer.parseInt(parts[1]); i++) {
                    treeSet.add(i);
                }
            } else {
                treeSet.add(Integer.parseInt(partition));
            }
        }
        treeSet.remove(target);

        Integer prev = null;
        Integer start = null;
        List<String> result = new ArrayList<>();

        for (int v : treeSet) {
            if (start == null) {
                start = v;
                prev = v;
            } else if (v == prev + 1) {
                prev = v;
            } else {
                if (start.equals(prev)) {
                    result.add(String.valueOf(start));
                } else {
                    result.add(start + "-" + prev);
                }
                start = prev = v;
            }
        }

        if (start != null) {
            if (start.equals(prev)) {
                result.add(String.valueOf(start));
            } else {
                result.add(start + "-" + prev);
            }
        }
        System.out.println(String.join(",", result));
    }

}
