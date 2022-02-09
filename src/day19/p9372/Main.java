package day19.p9372;

import java.util.*;
import java.io.*;

public class Main {

    static int T;
    static int N, M;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day19/p9372/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
            }

            sb.append(N - 1 + "\n");
        }

        System.out.print(sb.toString());
    }
}
