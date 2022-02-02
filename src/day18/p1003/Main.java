package day18.p1003;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] dp = new int[41][2];
    static int one = 0, zero = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day18/p1003/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        //2차배열의 0은 0, 1은 1
        dp[0][0] = dp[1][1] = 1;

        int[] result;
        for (int i = 0; i < N; i++) {
            int M = Integer.parseInt(br.readLine());

            if (dp[M][0] == 0 && dp[M][1] == 0) {
                result = getFib(M);
            } else {
                result = dp[M];
            }
            sb.append(result[0] + " " + result[1] + "\n");
        }

        System.out.println(sb.toString());
    }

    public static int[] getFib(int n) {

        if (dp[n][0] == 0 && dp[n][1] == 0) {
            dp[n][0] = getFib(n - 1)[0] + getFib(n - 2)[0];
            dp[n][1] = getFib(n - 1)[1] + getFib(n - 2)[1];
        }
        return dp[n];
    }
}
