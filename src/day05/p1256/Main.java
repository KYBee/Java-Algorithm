package day05.p1256;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] pascal;
    static StringBuilder sb = new StringBuilder();

    public static int combination(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        } else if (pascal[n][r] != 0) {
            return pascal[n][r];
        } else {
            return pascal[n][r] = Math.min((int) 1e9, combination(n - 1, r - 1) + combination(n - 1, r));
        }
    }

    public static void query(int n, int m, int k) {
        while (n >= 0 || m >= 0) {

            if (n == 0) {
                //z 만 남은 경우
                while (m > 0) {
                    sb.append("z");
                    m--;
                }
                break;
            } else if (m == 0) {
                //a 만 남은 경우
                while (n > 0) {
                    sb.append("a");
                    n--;
                }
                break;
            } else {
                int leftCount = combination(n + m - 1, m);
                if (leftCount >= k) {
                    sb.append("a");
                    n--;
                } else {
                    sb.append("z");
                    m--;
                    k -= leftCount;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day05/p1256/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        pascal = new int[201][201];

        if (K > combination(N + M, M)) {
            System.out.println(-1);
        } else {
            query(N, M, K);
        }

        System.out.println(sb);
    }
}
