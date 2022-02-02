package day17.p9252;

import java.util.*;
import java.io.*;

/**
 * 대표적인 LSC 문제
 * */

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int aLength;
    static int bLength;
    static String a;
    static String b;
    static char[] aArray;
    static char[] bArray;
    static List<Character> result = new LinkedList<>();

    static int[][] dp;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day17/p9252/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        a = br.readLine();
        b = br.readLine();

        aArray = a.toCharArray();
        bArray = b.toCharArray();

        aLength = aArray.length;
        bLength = bArray.length;

        dp = new int[aLength + 1][bLength + 1];

        for (int i = 0; i < aLength; i++) {
            for (int j = 0; j < bLength; j++) {
                if (aArray[i] == bArray[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        int current = dp[aLength][bLength];
        sb.append(current + "\n");

        if (current != 0) {
            while (current > 0) {
                if (dp[aLength - 1][bLength] == current){
                    current = dp[--aLength][bLength];
                } else if (dp[aLength][bLength - 1] == current) {
                    current = dp[aLength][--bLength];
                } else {
                    current = dp[--aLength][--bLength];
                    result.add(aArray[aLength]);
                }
            }

            Collections.reverse(result);

            for (char c : result) {
                sb.append(c);
            }
        }

        System.out.println(sb.toString());
    }
}
