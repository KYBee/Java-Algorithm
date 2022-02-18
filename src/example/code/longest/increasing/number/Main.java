package example.code.longest.increasing.number;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] numList;
    static int[] order;
    static int[] dp;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day31/p11054/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numList = new int[N];
        dp = new int[N];
        order = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        int index = 0;
        dp[index] = numList[index];
        order[index] = index;

        for (int i = 1; i < N; i++) {

            int newIdx = order[i] = binarySearch(dp, numList[i], index + 1);

            if (index < newIdx) {
                dp[++index] = numList[i];
            } else {
                dp[newIdx] = numList[i];
            }
        }

        System.out.println(index + 1);
        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1; i >= 0 ; i--) {
            if (order[i] == index) {
                stack.push(numList[i]);
                index--;
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    //Lower Bound
    public static int binarySearch(int[] list, int target, int length) {
        int start = 0, end = length;

        while (start < end) {
            int mid = (start + end) / 2;

            if (target <= list[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}

