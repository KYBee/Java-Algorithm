package day26.p2268;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, S = 1;
    static long[] tree;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day26/p2268/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        while (S < N) {
            S *= 2;
        }

        tree = new long[S * 2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 0) {

                if (a > b) {
                    int temp = a;
                    a = b;
                    b = temp;
                }

                sb.append(query(1, S, 1, a, b)).append("\n");
            } else {
                long diff = b - tree[S + a - 1];
                update(1, S, 1, a, diff);
            }
        }

        System.out.print(sb);
    }

    public static long query(int left, int right, int node, int leftQuery, int rightQuery) {
        if (right < leftQuery || rightQuery < left) {
            return 0;
        } else if (leftQuery <= left && right <= rightQuery) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            long leftVal  = query(left, mid, node * 2, leftQuery, rightQuery);
            long rightVal = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return leftVal + rightVal;
        }
    }

    public static void update(int left, int right, int node, int target, long diff) {
        if (right < target || target < left) {
            return;
        } else {
            tree[node] += diff;

            if (right != left) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }
}
