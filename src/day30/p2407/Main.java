package day30.p2407;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {

    static int N, M;
    static BigInteger[][] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day30/p2407/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        dp = new BigInteger[N + 1][N + 1];

        System.out.println(getCombination(N, M));
    }

    public static BigInteger getCombination(int n, int m) {
        if (m == 0 || n == m) {
            return dp[n][m] = new BigInteger("1");
        } else if (dp[n][m] != null) {
            return dp[n][m];
        } else {
            return dp[n][m] = getCombination(n - 1, m - 1).add(getCombination(n - 1, m));
        }
    }
}
