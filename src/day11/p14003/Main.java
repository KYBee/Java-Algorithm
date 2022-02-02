package day11.p14003;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;
    static int[] dp;
    static int[] prev;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day11/p14003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        numList = new int[N + 1];
        dp = new int[N + 1];
        prev = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = 1;
        prev[1] = numList[1];

        int length = 1;
        for (int i = 2; i <= N; i++) {

            int newIdx = dp[i] = upperBound(numList[i], length);

            if(length < newIdx) {
                prev[++length] = numList[i];
            } else {
                prev[newIdx] = numList[i];
            }
        }

        sb.append(length + "\n");

        int[] result = new int[length];
        while (length > 0) {
            if (dp[N] == length) {
                result[length - 1] = numList[N];
                length--;
            }
            N--;
        }

        for (int n : result) {
            sb.append(n + " ");
        }
        System.out.println(sb.toString());
    }

    public static int upperBound(int target, int length) {

        int start = 1, end = length + 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (target <= prev[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
