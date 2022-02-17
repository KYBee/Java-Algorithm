package day28.p1699;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day28/p1699/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        //1의 제곱으로만 계산하는 경우의 수 기록
        for (int i = 1; i <= N; i++) {
            dp[i] = i;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 2; j * j <= i ; j++) {
                dp[i] = Math.min(dp[i - j * j] + 1, dp[i]);
            }
        }
        
        System.out.println(dp[N]);
    }
}
