package day16.p11505;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static long[] indexedTree;
    static long[] numList;
    static int S;

    static final long MOD_VALUE = 1000000007;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day16/p11505/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numList = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numList[i] = Long.parseLong(st.nextToken());
        }

        // Init
        init();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                //업데이트
                int target = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                update(1, S, 1, target, to);
            } else {
                //쿼리

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                long result = query(1, S, 1, from, to);

                System.out.println(result);
            }
        }




    }

    public static void init() {

        S = 1;
        while (S < N) {
            S *= 2;
        }

        indexedTree = new long[2 * S];

        for (int i = 0; i < N; i++) {
            indexedTree[S + i] = numList[i] % MOD_VALUE;
        }
        for (int i = S + N; i < 2 * S; i++) {
            indexedTree[i] = 1;
        }

        for (int i = S - 1; i > 0; i--) {
            indexedTree[i] = (indexedTree[2 * i] * indexedTree[2 * i + 1]) % MOD_VALUE;
        }

    }

    public static void update(int left, int right, int node, int target, int to) {

        // 구간을 나누기
        if (target < left || right < target) {
            return;
        } else {

            if (left == right) {
                indexedTree[node] = to;
                return;
            }

            int mid = (left + right) / 2;
            update(left, mid, node * 2, target, to);
            update(mid + 1, right, node * 2 + 1, target, to);

            indexedTree[node] = (indexedTree[node * 2] * indexedTree[node * 2 + 1]) % MOD_VALUE;
        }

    }

    public static long query(int left, int right, int node, int leftQuery, int rightQuery) {

        //구간을 나누기
        if (right < leftQuery || rightQuery < left) {
            return 1;
        } else if (leftQuery <= left && right <= rightQuery) {
            return indexedTree[node];
        } else {

            //구간을 나눠야 함
            int mid = (left + right) / 2;
            long leftValue = query(left, mid, node * 2, leftQuery, rightQuery);
            long rightValue = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);

            return (leftValue * rightValue) % MOD_VALUE;
        }
    }
}
