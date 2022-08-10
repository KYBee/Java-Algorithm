package day32.p1654;

import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static long k;
    static long[] line;
    static long answer = 0;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day32/p1654/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());

        line = new long[n];

        for (int i = 0; i < n; i++) {
            line[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(line);

        long start = 1, end = line[line.length - 1];

        while (start <= end) {
            long mid = (start + end) / 2;
            long lanLine = 0;

            for (long l : line) {
                lanLine += l / mid;
            }

            if (lanLine < k) {
                end = mid - 1;
            } else {
                start = mid + 1;
                answer = mid > answer ? mid : answer;
            }
        }

        System.out.println(answer);

    }
}
