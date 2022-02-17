package day26.p1615;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, S = 1;
    static int[] topTree;
    static int[] bottomTree;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day26/p1615/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        while (S < N) {
            S *= 2;
        }
        topTree = new int[S * 2];
        bottomTree = new int[S * 2];

        int topCount = 0, bottomCount = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int top = Integer.parseInt(st.nextToken());
            int bottom = Integer.parseInt(st.nextToken());

            update(topTree, 1, S, 1, top);
            update(bottomTree, 1, S, 1, bottom);

            if (top > bottom) {
                topCount = query(topTree, 1, S, 1, 1, top - 1);
                bottomCount = query(bottomTree, 1, S, 1, bottom + 1, S);
                answer += Math.min(topCount, bottomCount);
            } else if (top < bottom) {
                topCount = query(topTree, 1, S, 1, top + 1, S);
                bottomCount = query(bottomTree, 1, S, 1, 1, bottom - 1);
                answer += Math.min(topCount, bottomCount);
            } else {
                topCount = query(topTree, 1, S, 1, 1, top - 1);
                bottomCount = query(bottomTree, 1, S, 1, bottom + 1, S);
                answer += Math.min(topCount, bottomCount);

                topCount = query(topTree, 1, S, 1, top + 1, S);
                bottomCount = query(bottomTree, 1, S, 1, 1, bottom - 1);
                answer += Math.min(topCount, bottomCount);
            }
        }

        System.out.println(answer);
    }

    public static int query(int[] data, int left, int right, int node, int leftQuery, int rightQuery) {
        if (right < leftQuery || rightQuery < left) {
            return 0;
        } else if (leftQuery <= left && right <= rightQuery) {
            return data[node];
        } else {
            int mid = (left + right) / 2;
            int leftVal = query(data, left, mid, node * 2, leftQuery, rightQuery);
            int rightVal = query(data, mid + 1, right, node * 2 + 1, leftQuery, rightQuery);

            return leftVal + rightVal;
        }
    }

    public static void update(int[] data, int left, int right, int node, int target) {
        if (target < left || right < target) {
            return;
        } else {
            data[node]++;
            if (left != right) {
                int mid = (left + right) / 2;
                update(data, left, mid, node * 2, target);
                update(data, mid + 1, right, node * 2 + 1, target);
            }
        }
    }
}

