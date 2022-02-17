package day27.p9461;

import java.util.*;
import java.io.*;

public class Main {

    static long TC;
    static long[] dp = new long[101];

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day27/p9461/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 1;

        for (int i = 0; i < TC; i++) {
            int N = Integer.parseInt(br.readLine());

            System.out.println(getValue(N));
        }
    }

    public static long getValue(int n) {
        if (n <= 0) {
            return dp[0];
        } else if (dp[n] == 0) {
            dp[n] = getValue(n - 2) + getValue(n - 3);
        }

        return dp[n];
    }
}
