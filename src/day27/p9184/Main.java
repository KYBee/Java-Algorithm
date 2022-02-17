package day27.p9184;

import java.util.*;
import java.io.*;

public class Main {

    static int[][][] dp = new int[21][21][21];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day27/p9184/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1) {
                break;
            }

            int answer = getAnswer(a, b, c);



            sb.append("w(" + a + ", " + b + ", " + c + ") = " + answer + "\n");
        }

        System.out.println(sb);
    }


    public static int getAnswer(int a, int b, int c) {

        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        } else if (a > 20 || b > 20 || c > 20) {
            return getAnswer(20, 20, 20);
        }

        if (dp[a][b][c] == 0) {
            if (a < b && b < c) {
                dp[a][b][c] = getAnswer(a, b, c - 1) + getAnswer(a, b - 1, c - 1) - getAnswer(a, b - 1, c);
            } else {
                dp[a][b][c] = getAnswer(a - 1, b, c) + getAnswer(a - 1, b - 1, c) + getAnswer(a - 1, b, c - 1) - getAnswer(a - 1, b - 1, c - 1);
            }
        }

        return dp[a][b][c];
    }
}
