package day31.p13305;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static long[] distance;
    static long[] fee;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day31/p13305/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = N - 1;

        distance = new long[M];
        fee = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            distance[i] = Long.parseLong(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fee[i] = Long.parseLong(st.nextToken());
        }

        long total = distance[0] * fee[0];

        long min = fee[0];
        for (int i = 1; i < M; i++) {
            min = Math.min(min, fee[i]);
            total += min * distance[i];
        }

        System.out.println(total);
    }
}
