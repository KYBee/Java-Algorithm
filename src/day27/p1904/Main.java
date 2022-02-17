package day27.p1904;

import java.util.*;
import java.io.*;

public class Main {

    static final int mod = 15746;
    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day27/p1904/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        dp[1] = 1;
        if (N > 1) {
            dp[2] = 2;
        }

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] % mod + dp[i - 2] % mod) % mod;
        }

        System.out.println(dp[N] % mod);
    }
}
