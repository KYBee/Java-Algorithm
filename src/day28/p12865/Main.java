//TODO : 1차 배열로 한번 구현해보기

package day28.p12865;

import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static Integer[][] dp;
    static int[][] items;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day28/p12865/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new Integer [N + 1][K + 1];
        // 0 무게 1 가치
        items = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            items[i][0] = weight;
            items[i][1] = value;
        }

//        int max = 0;
//        for (int i = 1; i <= K; i++) {
//            for (int j = 1; j <= N; j++) {
//                if (items[j][0] > i) {
//                    dp[j][i] = dp[j - 1][i];
//                } else {
//                    dp[j][i] = Math.max(dp[j - 1][i - items[j][0]] + items[j][1], dp[j - 1][i]);
//                    max = Math.max(max, dp[j][i]);
//                }
//            }
//        }



        System.out.println(knapsack(N, K));
    }

    public static int knapsack(int n, int k) {

        if (n < 1 || k < 1) {
            return 0;
        }

        if (dp[n][k] == null) {
            if (items[n][0] > k) {
                dp[n][k] = knapsack(n - 1, k);
            } else {
                dp[n][k] = Math.max(knapsack(n - 1, k - items[n][0]) + items[n][1], knapsack(n - 1, k));
            }
        }

        return dp[n][k];
    }
}
