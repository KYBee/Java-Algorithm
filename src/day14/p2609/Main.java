package day14.p2609;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day14/p2609/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int g = getGCD(N, M);
        int l = g * (N / g) * (M / g);

        System.out.println(g);
        System.out.println(l);
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
