package day16.p11053;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day16/p11053/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        numList = new int[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (numList[i] > numList[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = -1;
        for(int i = 0; i < N; i++) {
            max = dp[i] > max ? dp[i] : max;
        }
        System.out.println(max);
    }
}
