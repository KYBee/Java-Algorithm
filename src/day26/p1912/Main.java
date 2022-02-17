package day26.p1912;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] numList;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day26/p1912/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numList = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = numList[0];
        int answer = dp[0];

        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(numList[i], dp[i - 1] + numList[i]);
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
