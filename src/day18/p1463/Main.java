package day18.p1463;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] dp;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day18/p1463/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        int idx = 2;

        Integer[] possibility = new Integer[3];
        while (idx <= N) {

            dp[idx] = dp[idx - 1] + 1;

            if (idx % 3 == 0) {
                dp[idx] = Math.min(dp[idx], dp[idx / 3] + 1);
            }

            if (idx % 2 == 0) {
                dp[idx] = Math.min(dp[idx], dp[idx / 2] + 1);
            }

            idx++;
        }

        System.out.println(dp[N]);
    }
}
