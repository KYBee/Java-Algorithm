package day18.p1915;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] dp;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day18/p1915/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();

            for (int j = 1; j <= M; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
                dp[i][j] = map[i][j];
            }
        }


        for (int i = 1; i <=N ; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]) + 1;
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        System.out.println(answer * answer);
    }
}
