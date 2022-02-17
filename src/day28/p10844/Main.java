package day28.p10844;

import java.util.*;
import java.io.*;

public class Main {
    static final int mod = 1000000000;
    static int N;
    static long[][] dp;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day28/p10844/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new long[N][10];
        for (int i = 1; i < 10; i++) {
            dp[0][i] = 1;
        }
        
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][1] % mod;
            dp[i][9] = dp[i - 1][8] % mod;
            for (int j = 1; j < 9; j++) {
                dp[i][j] = (dp[i - 1][j - 1] % mod + dp[i - 1][j + 1] % mod) % mod;
            }
        }

        long answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp[N - 1][i] % mod;
        }
        System.out.println(answer % mod);
    }
}
