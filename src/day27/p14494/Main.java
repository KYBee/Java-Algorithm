package day27.p14494;

import java.util.*;
import java.io.*;

public class Main {

    static final int mod = 1000000007;
    static int N, M;
    static long[][] dp;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day27/p14494/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new long[N + 1][M + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = (dp[i - 1][j - 1] % mod + dp[i - 1][j] % mod + dp[i][j - 1] % mod) % mod;
            }
        }

        System.out.println(dp[N][M] % mod);
    }
}
