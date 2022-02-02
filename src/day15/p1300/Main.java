package day15.p1300;

import java.util.*;
import java.io.*;

public class Main {

    static int N, K;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day15/p1300/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        long start = 1;
        long end = K;

        while (start < end) {

            long mid = (start + end) / 2;
            long count = 0;

            for (int i = 1; i <= N; i++) {
                count += Math.min(N, mid / i);
            }

            if (K <= count) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }
}
