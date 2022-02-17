package day28.p14501;

import java.util.*;
import java.io.*;

public class Main {

    static int N;

    static int[] time;
    static int[] money;

    static int[] dp;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day28/p14501/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        time = new int[N + 1];
        money = new int[N + 1];

        dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            time[i] = t;
            money[i] = p;
        }

        int max = 0;

        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dp[i]);

            int end = time[i] + i;

            if (end <= N + 1) {
                dp[end] = Math.max(dp[end], max + money[i]);
            }
        }

        System.out.println(Math.max(max, dp[N + 1]));

    }
}