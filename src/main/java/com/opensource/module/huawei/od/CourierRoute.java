package com.opensource.module.huawei.od;

import java.util.*;

public class CourierRoute {

    static final long INF = (long) 1e16;


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            System.out.println(-1);
            return;
        }

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Map<Integer, Integer> idToIdx = new HashMap<>();
        long[] distStation = new long[n];
        Arrays.fill(distStation, INF);

        for (int i = 0; i < n; i++) {
            int cid = scanner.nextInt();
            long d = scanner.nextLong();
            idToIdx.put(cid, i);
            distStation[i] = d;
        }

        long[][] direct = new long[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(direct[i], INF);
        for (int i = 0; i < n; i++) direct[i][i] = 0;

        for (int k = 0; k < m; k++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            long d = scanner.nextLong();

            Integer ai = idToIdx.get(a);
            Integer bi = idToIdx.get(b);
            if (ai == null || bi == null) continue;

            direct[ai][bi] = Math.min(direct[ai][bi], d);
            direct[bi][ai] = Math.min(direct[bi][ai], d);
        }

        long[][] dist = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long directPath = direct[i][j];
                long viaStation = INF;
                if (distStation[i] < INF && distStation[j] < INF) {
                    viaStation = distStation[i] + distStation[j];
                }
                dist[i][j] = Math.min(directPath, viaStation);
            }
        }

        int fullMask = (1 << n) - 1;
        long[][] dp = new long[1 << n][n];
        for (int mask = 0; mask <= fullMask; mask++) Arrays.fill(dp[mask], INF);

        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = distStation[i];
        }

        for (int mask = 0; mask <= fullMask; mask++) {
            for (int last = 0; last < n; last++) {
                if ((mask & (1 << last)) == 0) continue;
                long cur = dp[mask][last];
                if (cur >= INF) continue;

                for (int nxt = 0; nxt < n; nxt++) {
                    if ((mask & (1 << nxt)) != 0) continue;
                    if (dist[last][nxt] >= INF) continue;

                    int nmask = mask | (1 << nxt);
                    long nd = cur + dist[last][nxt];
                    dp[nmask][nxt] = Math.min(dp[nmask][nxt], nd);
                }
            }
        }

        long ans = INF;
        for (int last = 0; last < n; last++) {
            if (dp[fullMask][last] < INF) {
                ans = Math.min(ans, dp[fullMask][last] + distStation[last]);
            }
        }

        System.out.println(ans >= INF ? -1 : ans);
    }
}
