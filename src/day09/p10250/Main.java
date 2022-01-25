package day09.p10250;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, W, H, T;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day09/p10250/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            int a;
            int b;

            if (T % H != 0) {
                a = T / H + 1;
                b = T % H;
            } else {
                a = T / H;
                b = H;
            }

            System.out.println(b * 100 + a);
        }
    }
}
