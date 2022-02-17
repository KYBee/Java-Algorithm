package day27.p9095;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] dp = new int[11];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day27/p9095/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            System.out.println(getNumber(num));
        }
    }

    public static int getNumber(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return dp[n];
        } else if (dp[n] != 0) {
            return dp[n];
        } else {
            return dp[n] = getNumber(n - 1) + getNumber(n - 2) + getNumber(n - 3);
        }
    }
}

