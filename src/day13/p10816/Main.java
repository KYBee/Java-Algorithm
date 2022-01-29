package day13.p10816;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long[] numList;
    static int M;
    static long[] target;
    static long[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day13/p10816/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        numList = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        target = new long[M];
        result = new long[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            target[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(numList);


        for (long num : target) {

            int left = lower(num, 0, N);
            int right = upper(num, 0, N);

            sb.append(right - left).append(" ");
        }

        System.out.println(sb.toString());
    }

    public static int lower(long target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;

            if (target <= numList[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static int upper(long target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;

            if (target < numList[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
