package day14.p1934;

import java.util.*;
import java.io.*;

public class Main {

    static int T;
    static int N, M;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day14/p1934/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int g = getGCD(N, M);
            int l = g * (N / g) * (M / g);

            System.out.println(l);
        }
    }

    public static int getGCD(int a, int b) {

        int r = a % b;

        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }

        return b;
    }


}
