package com.opensource.module.huawei.od;

import java.util.*;

public class StudentSort_053 {

    public static class Student {
        String name;
        int[] scores;
        int total;

        public Student(String name, int[] scores) {
            this.name = name;
            this.scores = scores;
            this.total = 0;
            for (int score : scores) {
                this.total += score;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取学生人数和科目数量
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        // 读取科目名称
        String[] subjects = scanner.nextLine().split(" ");

        // 建立科目名称到索引的映射
        Map<String, Integer> subjectIndex = new HashMap<>();
        for (int i = 0; i < m; i++) {
            subjectIndex.put(subjects[i], i);
        }

        // 读取学生信息
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().split(" ");
            String name = parts[0];
            int[] scores = new int[m];
            for (int j = 0; j < m; j++) {
                scores[j] = Integer.parseInt(parts[j + 1]);
            }
            students.add(new Student(name, scores));
        }

        // 读取排序科目
        String sortSubject = scanner.nextLine();

        // 排序
        if (subjectIndex.containsKey(sortSubject)) {
            int index = subjectIndex.get(sortSubject);
            students.sort((s1, s2) -> {
                if (s2.scores[index] != s1.scores[index]) {
                    return s2.scores[index] - s1.scores[index]; // 降序
                }
                return s1.name.compareTo(s2.name); // 姓名升序
            });
        } else {
            // 按总分排序
            students.sort((s1, s2) -> {
                if (s2.total != s1.total) {
                    return s2.total - s1.total; // 降序
                }
                return s1.name.compareTo(s2.name); // 姓名升序
            });
        }

        // 输出结果
        for (int i = 0; i < students.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(students.get(i).name);
        }
        System.out.println();
    }
}
