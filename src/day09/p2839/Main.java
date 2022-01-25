package day09.p2839;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int five, three;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day09/p2839/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        while (N % 5 != 0) {
            N -= 3;
            three++;

            if (N < 3 && N != 0) {
                System.out.println(-1);
                return;
            }
        }
        five = N / 5;

        System.out.println(three + five);
    }
}
