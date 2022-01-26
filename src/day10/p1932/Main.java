package day10.p1932;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] triangle;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day10/p1932/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        triangle = new int[N + 1][N + 1];

        int current;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= i; j++) {
                current = Integer.parseInt(st.nextToken());
                triangle[i][j] = current + Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
        }

        int max = triangle[N][1];
        for (int i = 2; i <= N; i++) {
            if (max < triangle[N][i]) {
                max = triangle[N][i];
            }
        }

        System.out.println(max);
    }
}
