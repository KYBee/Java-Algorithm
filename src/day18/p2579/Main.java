package day18.p2579;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] stairs;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day18/p2579/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        stairs = new int[N];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = stairs[0];
        int result = getPath(N - 1);
        System.out.println(result);
    }

    public static int getPath(int n) {

        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return dp[0];
        } else if (dp[n] != 0) {
            return dp[n];
        } else {
            int d1 = getPath(n - 3) + stairs[n] + stairs[n - 1];
            int d2 = getPath(n - 2) + stairs[n];
            return dp[n] = Math.max(d1, d2);
        }
    }
}
