
package com.opensource.module.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CpuChoose {
    public static void main1(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        Integer[] cores = Arrays.stream(in.nextLine().split("[\\[\\]\\,\\s]"))
                .filter(str -> !"".equals(str))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        int target = in.nextInt();

        //初始化两个链路剩余可用的处理器
        ArrayList<Integer> processors_1 = new ArrayList<>();
        ArrayList<Integer> processors_2 = new ArrayList<>();

        Arrays.sort(cores, (a, b) -> a - b);
        for (Integer core : cores) {
            if (core < 4) {
                processors_1.add(core);
            } else {
                processors_2.add(core);
            }
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int length_1 = processors_1.size();
        int length_2 = processors_2.size();

        switch (target) {
            case 1:
                if (length_1 == 1 || length_2 == 1) {
                    if (length_1 == 1) dfs(processors_1, 0, 1, new ArrayList<>(), result);
                    if (length_2 == 1) dfs(processors_2, 0, 1, new ArrayList<>(), result);
                } else if (length_1 == 3 || length_2 == 3) {
                    if (length_1 == 3) dfs(processors_1, 0, 1, new ArrayList<>(), result);
                    if (length_2 == 3) dfs(processors_2, 0, 1, new ArrayList<>(), result);
                } else if (length_1 == 2 || length_2 == 2) {
                    if (length_1 == 2) dfs(processors_1, 0, 1, new ArrayList<>(), result);
                    if (length_2 == 2) dfs(processors_2, 0, 1, new ArrayList<>(), result);
                } else if (length_1 == 4 || length_2 == 4) {
                    if (length_1 == 4) dfs(processors_1, 0, 1, new ArrayList<>(), result);
                    if (length_2 == 4) dfs(processors_2, 0, 1, new ArrayList<>(), result);
                }
                break;
            case 2:
                if (length_1 == 2 || length_2 == 2) {
                    if (length_1 == 2) dfs(processors_1, 0, 2, new ArrayList<>(), result);
                    if (length_2 == 2) dfs(processors_2, 0, 2, new ArrayList<>(), result);
                } else if (length_1 == 4 || length_2 == 4) {
                    if (length_1 == 4) dfs(processors_1, 0, 2, new ArrayList<>(), result);
                    if (length_2 == 4) dfs(processors_2, 0, 2, new ArrayList<>(), result);
                } else if (length_1 == 3 || length_2 == 3) {
                    if (length_1 == 3) dfs(processors_1, 0, 2, new ArrayList<>(), result);
                    if (length_2 == 3) dfs(processors_2, 0, 2, new ArrayList<>(), result);
                }
                break;
            case 4:
                if (length_1 == 4 || length_2 == 4) {
                    if (length_1 == 4) result.add(processors_1);
                    if (length_2 == 4) result.add(processors_2);
                }
                break;
            case 8:
                if (length_1 == 4 && length_2 == 4) {
                    result.add(
                            Stream.concat(processors_1.stream(), processors_2.stream())
                                    .collect(Collectors.toCollection(ArrayList<Integer>::new)));
                }
                break;
        }
        System.out.println(result.toString());
    }

    public static void dfs(List<Integer> cores,int index,int level,ArrayList<Integer> path,ArrayList<ArrayList<Integer>> res) {
//        if (path.size() == level) {
//            res.add((ArrayList<Integer>) path.clone());
//            return;
//        }
        if(index == 4){
            System.out.println("====");
        }
        for (int i = index; i < cores.size(); i++) {
            System.out.println(i);
            //path.add(cores.get(i));
            // 逐个往后找合适的组合
            dfs(cores, i + 1, level, path, res);
            //path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<Integer> cores = Arrays.asList(1,2,3,4);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        dfs(cores,0,3,new ArrayList<>(),res);
        System.out.println(Arrays.deepToString(res.toArray()));

    }

}