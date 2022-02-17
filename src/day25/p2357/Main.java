package day25.p2357;

import java.util.*;
import java.io.*;

public class Main {

    static final int INF = 1000000001;
    static int N, M, S = 1;
    static int[] numList;
    static int[] minTree;
    static int[] maxTree;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day25/p2357/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        while (S < N) {
            S *= 2;
        }

        numList = new int[N];
        minTree = new int[S * 2];
        maxTree = new int[S * 2];

        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(br.readLine());
        }

        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int minNum = minQuery(1, S, 1, a, b);
            int maxNum = maxQuery(1, S, 1, a, b);

            sb.append(minNum + " " + maxNum + "\n");
        }

        System.out.print(sb.toString());
    }

    public static void init() {

        for (int i = 0; i < N; i++) {
            minTree[S + i] = numList[i];
            maxTree[S + i] = numList[i];
        }

        for (int i = S - 1; i > 0; i--) {
            minTree[i] = Math.min(minTree[2 * i], minTree[2 * i + 1]);
            maxTree[i] = Math.max(maxTree[2 * i], maxTree[2 * i + 1]);
        }
    }

    public static int minQuery(int left, int right, int node, int leftQuery, int rightQuery) {
        if (right < leftQuery || rightQuery < left) {
            return INF;
        } else if (leftQuery <= left && right <= rightQuery) {
            return minTree[node];
        } else {
            int mid = (left + right) / 2;
            int leftMin = minQuery(left, mid, node * 2, leftQuery, rightQuery);
            int rightMin = minQuery(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return Math.min(leftMin, rightMin);
        }
    }

    public static int maxQuery(int left, int right, int node, int leftQuery, int rightQuery) {
        if (right < leftQuery || rightQuery < left) {
            return 0;
        } else if (leftQuery <= left && right <= rightQuery) {
            return maxTree[node];
        } else {
            int mid = (left + right) / 2;
            int leftMax = maxQuery(left, mid, node * 2, leftQuery, rightQuery);
            int rightMax = maxQuery(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return Math.max(leftMax, rightMax);
        }
    }
}
