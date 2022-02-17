package day28.p14916;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] dp;
    static int[] temp = {0, -1, 1, -1, 2, 1};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day28/p14916/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        if (N <= 5){
            System.out.println(temp[N]);
        } else {
            for (int i = 1; i <= 5; i++) {
                dp[i] = temp[i];
            }

            for (int i = 6; i <= N; i++) {
                int two = dp[i - 2];
                int five = dp[i - 5];

                if (two == -1 && five == -1) {
                    dp[i] = -1;
                } else if (two == -1) {
                    dp[i] = five + 1;
                } else if (five == -1) {
                    dp[i] = two + 1;
                } else {
                    dp[i] = Math.min(dp[i - 5], dp[i - 2]) + 1;
                }
            }
            System.out.println(dp[N]);
        }
    }
}
