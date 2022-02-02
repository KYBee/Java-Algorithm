package day17.p11055;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day17/p11055/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        numList = new int[N + 1];
        dp = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            dp[i] = numList[i];

            for (int j = i - 1; j >= 1; j--) {
                if (numList[i] > numList[j] && dp[i] < dp[j] + numList[i]) {
                    dp[i] = dp[j] + numList[i];
                }
            }
        }

        int max = 0;
        for (int n : dp) {
            if (max < n) {
                max = n;
            }
        }
        System.out.println(max);
    }
}
