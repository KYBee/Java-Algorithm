package day09.p2775;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, k, n;
    static int[][] apartment = new int[15][15];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day09/p2775/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        for (int j = 1; j <= 14; j++) {
            apartment[0][j] = j;
            apartment[j][1] = 1;
        }


        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            System.out.println(getPeople(k, n));
        }

    }

    public static int getPeople(int k, int n) {
        if (k == 0 || n == 1) {
            return apartment[k][n];
        }

        if (apartment[k][n] != 0) {
            return apartment[k][n];
        } else {
            return apartment[k][n] = getPeople(k - 1, n) + getPeople(k, n - 1);
        }
    }
}
