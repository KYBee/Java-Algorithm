package day17.p1958;

import java.util.*;
import java.io.*;

/**
 * 대표적인 LSC 문제
 * a : abbbccc
 * b : bbbaddd
 * c : cccddda
 * */
public class Main {

    static int aLength, bLength, cLength;
    static String a, b, c;
    static char[] aArray, bArray, cArray;

    static int[][][] dp;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day17/p1958/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        a = br.readLine();
        b = br.readLine();
        c = br.readLine();

        aArray = a.toCharArray();
        bArray = b.toCharArray();
        cArray = c.toCharArray();

        aLength = aArray.length;
        bLength = bArray.length;
        cLength = cArray.length;

        dp = new int[aLength + 1][bLength + 1][cLength + 1];

        for (int i = 0; i < aLength; i++) {
            for (int j = 0; j < bLength; j++) {
                for (int k = 0; k < cLength; k++) {
                    if (aArray[i] == bArray[j] && bArray[j] == cArray[k]) {
                        dp[i + 1][j + 1][k + 1] = dp[i][j][k] + 1;
                    } else {
                        dp[i + 1][j + 1][k + 1] = Math.max(dp[i][j + 1][k + 1], Math.max(dp[i + 1][j + 1][k], dp[i + 1][j][k + 1]));
                    }
                }
            }
        }

        int current = dp[aLength][bLength][cLength];
        System.out.println(current);
    }
}