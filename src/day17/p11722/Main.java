package day17.p11722;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day17/p11722/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        numList = new int[N + 1];
        dp = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        int length = 1;
        dp[1] = numList[1];
        for (int i = 2; i <= N; i++) {

            if (dp[length] == numList[i]) {
                continue;
            } else if (dp[length] > numList[i]) {
                dp[++length] = numList[i];
            } else {
                int newIdx = binarySearch(numList[i], length);
                dp[newIdx] = numList[i];
            }
        }

        System.out.println(length);
    }

    public static int binarySearch(int target, int length) {
        int start = 1, end = length;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (target == dp[mid]) {
                return mid;
            } else if (dp[mid] < target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
