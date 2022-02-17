package day27.p11726;

import java.util.*;
import java.io.*;

public class Main {

    static final int mod = 10007;
    static int N;
    static int[] dp = new int[1001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 2;

        System.out.println(getNumber(N) % 10007);
    }

    public static int getNumber(int n){
        if (n == 1 || n == 2) {
            return dp[n];
        } else if (dp[n] != 0) {
            return dp[n];
        } else {
            return dp[n] = (getNumber(n - 1) % mod + getNumber(n - 2) % mod) % mod;
        }
    }
}
