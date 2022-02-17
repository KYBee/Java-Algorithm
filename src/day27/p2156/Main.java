package day27.p2156;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] wine;
    static boolean[] visited;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day27/p2156/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        wine = new int[N + 1];
        dp = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 0;
        dp[1] = wine[1];
        visited[0] = true;
        visited[1] = true;
        if (N > 1) {
            dp[2] = wine[1] + wine[2];
            visited[2] = true;
        }

//        for (int i = 3; i <= N; i++) {
//            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 3] + wine[i - 1] + wine[i], dp[i - 2] + wine[i]));
//        }
//
//        System.out.println(dp[N]);

        System.out.println(getWine(N));
    }

    public static int getWine(int n){

        if (n <= 0) {
            return dp[0];
        } else if (visited[n] == false) {
            visited[n] = true;
            dp[n] = Math.max(getWine(n - 1), Math.max(getWine(n - 3) + wine[n - 1], getWine(n - 2)) + wine[n]);
        }

        return dp[n];
    }
}
