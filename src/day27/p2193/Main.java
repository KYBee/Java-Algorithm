package day27.p2193;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day27/p2193/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new long[2][91];

        dp[1][1] = 1;
        dp[0][2] = 1;

        for (int i = 3; i <= N; i++) {
            dp[0][i] = dp[0][i - 1] + dp[1][i - 1];
            dp[1][i] = dp[0][i - 1];
        }

        System.out.println(dp[0][N] + dp[1][N]);
    }
}
