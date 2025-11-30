package com.opensource.module.huawei.od;

import java.util.*;

public class CompressedLogs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取查询时间范围
        String queryLine = scanner.nextLine();
        String[] queryParts = queryLine.split(",");
        long queryStart = Long.parseLong(queryParts[0]);
        long queryEnd = Long.parseLong(queryParts[1]);

        // 读取记录行数
        int n = Integer.parseInt(scanner.nextLine());

        List<String> results = new ArrayList<>();

        // 处理每条压缩记录
        for (int i = 0; i < n; i++) {
            String recordLine = scanner.nextLine();
            String[] recordParts = recordLine.split(",");
            long recordStart = Long.parseLong(recordParts[0]);
            long recordEnd = Long.parseLong(recordParts[1]);
            String kpi = recordParts[2];

            // 计算与查询范围的交集
            long overlapStart = Math.max(queryStart, recordStart);
            long overlapEnd = Math.min(queryEnd, recordEnd);

            // 如果有交集，生成每分钟的数据
            if (overlapStart <= overlapEnd) {
                for (long time = overlapStart; time <= overlapEnd; time++) {
                    results.add(time + "," + kpi);
                }
            }
        }

        // 输出结果
        if (results.isEmpty()) {
            System.out.println(-1);
        } else {
            // 按时间排序（实际上已经按顺序添加，但为了确保）
            results.sort((a, b) -> {
                long timeA = Long.parseLong(a.split(",")[0]);
                long timeB = Long.parseLong(b.split(",")[0]);
                return Long.compare(timeA, timeB);
            });

            for (String result : results) {
                System.out.println(result);
            }
        }

        scanner.close();
    }
}
