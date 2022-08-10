package day32.p2512;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long M;
    static long answer = 0;
    static long[] requests;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day32/p2512/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        requests = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            requests[i] = Long.parseLong(st.nextToken());
        }

        M = Long.parseLong(br.readLine());

        Arrays.sort(requests);

        long start = 1, end = requests[requests.length - 1];

        while (start <= end) {

            long mid = (start + end) / 2;
            long total = 0;

            for (long r : requests) {
                total += Math.min(r, mid);
            }

            if (total > M) {
                end = mid - 1;
            } else {
                start = mid + 1;
                answer = mid > answer ? mid : answer;
            }
        }

        System.out.println(answer);

    }
}
