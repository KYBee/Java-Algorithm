package day17.p14002;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;
    static int[] dp;
    static int[] prev;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day17/p14002/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        numList = new int[N];
        dp = new int[N];
        prev = new int[N];

        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            prev[i] = 0;

            for (int j = i - 1; j >= 0; j--) {
                if (numList[i] > numList[j] && dp[i] < dp[j] + 1) {
                    // 이미 최대로 업데이트가 된 경우에는 그 숫자를 그대로 가져가야 하므로
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }

        int max = 0;
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (max < dp[i]) {
                max = dp[i];
                idx = i;
            }
        }

        int i = 0;
        int[] result = new int[max];
        while (max > 0) {
            result[i++] = numList[idx];
            max--;
            idx = prev[idx];
        }

        sb.append(result.length + "\n");
        for (int j = result.length - 1; j >= 0; j--) {
            sb.append(result[j] + " ");
        }

        System.out.println(sb.toString());
    }
}
