package example.code.LCS.string;

import java.util.*;
import java.io.*;

public class Main {

    static String s1;
    static String s2;
    static int s1Length;
    static int s2Length;

    static int[][] dp;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/example/code/LCS/string/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s1 = br.readLine();
        s2 = br.readLine();

        s1Length = s1.length();
        s2Length = s2.length();

        dp = new int[s1Length + 1][s2Length + 1];

        for (int i = 1; i <= s1Length; i++) {
            for (int j = 1; j <= s2Length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }


        for (int i = 0; i <= s1Length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        System.out.println(dp[s1Length][s2Length]);
        char [] result = new char[dp[s1Length][s2Length]];

        int current = dp[s1Length][s2Length];
        int x = s1Length;
        int y = s2Length;

        while (current > 0) {
            if (dp[x][y] == dp[x - 1][y] ) {
                x--;
            } else if (dp[x][y] == dp[x][y - 1]) {
                y--;
            } else {
                current--;
                result[current] = s2.charAt(--y);
                x--;
            }
        }

        System.out.println(Arrays.toString(result));
    }
}
