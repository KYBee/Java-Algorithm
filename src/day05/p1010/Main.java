package day05.p1010;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, N, M;
    static int pascal[][] = new int[30][30];

    public static int getBridgeCount(int n, int m) {

        if (m == 0 || n == m) {
            return 1;
        } else if (pascal[n][m] != 0) {
            return pascal[n][m];
        } else {
            pascal[n][m] = getBridgeCount(n - 1, m - 1) + getBridgeCount(n - 1, m);
            return pascal[n][m];
        }

    }

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day05/p1010/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            System.out.println(getBridgeCount(N, M));
        }
    }
}
