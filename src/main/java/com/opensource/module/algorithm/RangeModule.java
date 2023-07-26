package com.opensource.module.algorithm;

import java.util.*;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/25 21:45
 * @Version V1.0
 */
public class RangeModule {

    TreeMap<Integer, Integer> treeMap;

    public RangeModule() {
        this.treeMap = new TreeMap();
        this.treeMap.put(-1, -1);
        this.treeMap.put((int)1e9+1, (int)1e9+1);
    }

    public void addRange(int left, int right) {
        List<Map.Entry<Integer, Integer>> list = intersection(left, right);
        if (list.isEmpty()) {
            this.treeMap.put(left, right);
        } else {
            this.treeMap.put(Math.min(left, list.get(0).getKey()),
                    Math.max(right, list.get(list.size() - 1).getValue()));
        }
    }

    public void removeRange(int left, int right) {
        List<Map.Entry<Integer, Integer>> list = intersection(left, right);
        if (!list.isEmpty()) {
            if (list.get(0).getKey() < left) {
                this.treeMap.put(list.get(0).getKey(), left);
            }
            if (list.get(list.size() - 1).getValue() > right) {
                this.treeMap.put(right, list.get(list.size() - 1).getValue());
            }
        }
    }


    public boolean queryRange(int left, int right) {
        return this.treeMap.floorEntry(left).getValue() >= right;
    }

    private List<Map.Entry<Integer, Integer>> intersection(int left, int right) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        while (true) {
            Map.Entry<Integer, Integer> floorEntry = this.treeMap.floorEntry(left);
            if (floorEntry.getValue() >= left) {
                list.add(floorEntry);
                this.treeMap.remove(floorEntry.getKey());
            } else {
                break;
            }
        }

        while (true) {
            Map.Entry<Integer, Integer> ceilingEntry = this.treeMap.ceilingEntry(left);
            if (ceilingEntry.getKey() <= right) {
                list.add(ceilingEntry);
                this.treeMap.remove(ceilingEntry.getKey());
            } else {
                break;
            }
        }
        return list;
    }

}
