package day31.p11054;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] perfect;
    static int[] reverse;

    static int[] dp;

    static int[] perfectOrder;
    static int[] reverseOrder;

    static int[] finalOrder;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day31/p11054/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        perfect = new int[N];
        reverse = new int[N];

        dp = new int[N];

        perfectOrder = new int[N];
        reverseOrder = new int[N];

        finalOrder = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            perfect[i] = Integer.parseInt(st.nextToken());
            reverse[N - i - 1] = perfect[i];
        }

        int index = 0;
        dp[index] = perfect[index];
        perfectOrder[index] = index;

        for (int i = 1; i < N; i++) {
            int newIndex = perfectOrder[i] = search(dp, perfect[i], index + 1);

            if (index < newIndex) {
                dp[++index] = perfect[i];
            } else {
                dp[newIndex] = perfect[i];
            }
        }

        index = 0;
        Arrays.fill(dp, 0);
        dp[index] = reverse[index];
        reverseOrder[index] = index;

        for (int i = 1; i < N; i++) {
            int newIndex = reverseOrder[i] = search(dp, reverse[i], index + 1);

            if (index < newIndex) {
                dp[++index] = reverse[i];
            } else {
                dp[newIndex] = reverse[i];
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            finalOrder[i] = perfectOrder[i] + reverseOrder[N - i - 1];
            max = Math.max(max, finalOrder[i]);
        }
        System.out.println(max + 1);
    }

    public static int search(int[] arr, int target, int length) {
        int start = 0, end = length;

        while (start < end) {
            int mid = (start + end) / 2;

            if (target <= arr[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}
