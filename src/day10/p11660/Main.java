package day10.p11660;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] sumTable;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day10/p11660/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sumTable = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                sumTable[i][j] = Integer.parseInt(st.nextToken()) + sumTable[i - 1][j] + sumTable[i][j - 1] - sumTable[i - 1][j - 1];
            }
        }


        int x1, x2, y1, y2;
        int total;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            total = sumTable[x2][y2] - sumTable[x2][y1 - 1] - sumTable[x1 - 1][y2] + sumTable[x1 - 1][y1 - 1];
            System.out.println(total);
        }
    }
}
