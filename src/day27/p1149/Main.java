package day27.p1149;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day27/p1149/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                int current = Integer.parseInt(st.nextToken());
                dp[i][j] = current + Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
            }
        }

        int answer = 10000000;
        for (int i = 0; i < 3; i++) {
            answer = Math.min(answer, dp[N][i]);
        }
        

        System.out.println(answer);
    }
}
