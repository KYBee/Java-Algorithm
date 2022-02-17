package day26.p14438;

import java.util.*;
import java.io.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N, M, S = 1;
    static int[] numList;
    static long[] tree;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day26/p14438/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        while (S < N) {
            S *= 2;
        }

        st = new StringTokenizer(br.readLine());
        numList = new int[N];
        tree = new long[S * 2];
        Arrays.fill(tree, INF);

        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        init();
        
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 2) {
                sb.append(query(1, S, 1, a, b)).append("\n");
            } else {
                update(1, S, 1, a, b);
            }
        }

        System.out.println(sb.toString());
    }

    public static void init() {
        for (int i = 0; i < N; i++) {
            tree[S + i] = numList[i];
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    public static long query(int left, int right, int node, int leftQuery, int rightQuery) {
        if (right < leftQuery || rightQuery < left) {
            return INF;
        } else if (leftQuery <= left && right <= rightQuery) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            long leftValue = query(left, mid, node * 2, leftQuery, rightQuery);
            long rightValue = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return Math.min(leftValue, rightValue);
        }
    }

    public static long update(int left, int right, int node, int target, long value) {
        if (right < target || target < left) {
            return tree[node];
        } else {

            if (left == right) {
                return tree[node] = value;
            }

            int mid = (left + right) / 2;

            long leftValue = update(left, mid, node * 2, target, value);
            long rightValue = update(mid + 1, right, node * 2 + 1, target, value);

            return tree[node] = Math.min(leftValue, rightValue);
        }
    }
}
