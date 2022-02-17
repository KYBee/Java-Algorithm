package day25.p1275;

import java.util.*;
import java.io.*;

public class Main {

    static int N, Q, S = 1;
    static int x, y, a, b;

    static int[] numList;
    static long[] tree;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day25/p1275/input.txt"));
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        while (S < N) {
            S *= 2;
        }

        numList = new int[N];
        tree = new long[2 * S];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        init();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            if (b < a) {
                int temp = a;
                a = b;
                b = temp;
            }

            sb.append(query(1, S, 1,  a, b)).append("\n");
            long diff = y - tree[S + x - 1];
            update(1, S, 1, x,  diff);
        }

        System.out.print(sb.toString());
    }

    public static void init() {
        for (int i = 0; i < N; i++) {
            tree[S + i] = numList[i];
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    public static long query(int left, int right, int node, int leftQuery,  int rightQuery) {
        if (rightQuery < left || right < leftQuery) {
            return 0;
        } else if (leftQuery <= left && right <= rightQuery) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            long leftVal = query(left, mid, node * 2 , leftQuery, rightQuery);
            long rightVal = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);

            return leftVal + rightVal;
        }
    }

    public static void update(int left,  int right, int node, int target, long diff) {

        if (target < left || right < target) {
            return;
        } else if (left <= target || target <= right) {
            int  mid = (left + right) / 2;
            tree[node] += diff;

            if (left != right) {
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }
}
