package day26.p18436;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, S = 1;

    static int[] numList;
    static long[] evenTree;
    static long[] oddTree;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day26/p18436/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        while (S < N) {
            S *= 2;
        }
        
        numList = new int[N];
        evenTree = new long[S * 2];
        oddTree = new long[S * 2];

        st = new StringTokenizer(br.readLine());
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

            if (op == 1) {
                if (numList[a - 1] % 2 == b % 2) {
                    continue;
                } else {
                    if (numList[a - 1] % 2 == 0) {
                        update(evenTree, 1, S, 1, a, -1);
                        update(oddTree, 1, S, 1, a, 1);
                    } else {
                        update(oddTree, 1, S, 1, a, -1);
                        update(evenTree, 1, S, 1, a, 1);
                    }
                    numList[a - 1] = b;
                }
            } else if (op == 2) {
                sb.append(query(evenTree, 1, S, 1, a, b)).append("\n");
            } else {
                sb.append(query(oddTree,1, S, 1, a, b)).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void init() {
        for (int i = 0; i < N; i++) {
            if (numList[i] % 2 == 0) {
                evenTree[S + i] = 1;
            } else {
                oddTree[S + i] = 1;
            }
        }

        for (int i = S - 1; i > 0; i--) {
            evenTree[i] = evenTree[i * 2] + evenTree[i * 2 + 1];
            oddTree[i] = oddTree[i * 2] + oddTree[i * 2 + 1];
        }
    }

    public static long query(long[] data, int left, int right, int node, int leftQuery, int rightQuery) {
        if (right < leftQuery || rightQuery < left) {
            return 0;
        } else if (leftQuery <= left && right <= rightQuery) {
            return data[node];
        } else {
            int mid = (left + right) / 2;
            long leftVal = query(data, left, mid, node * 2, leftQuery, rightQuery);
            long rightVal = query(data, mid + 1, right, node * 2 + 1, leftQuery, rightQuery);

            return leftVal + rightVal;
        }
    }

    public static void update(long[] data, int left, int right, int node, int target, int diff) {
        if (right < target || target < left) {
            return;
        } else {
            data[node] += diff;

            if (left != right) {
                int mid = (left + right) / 2;

                update(data, left, mid, node * 2, target, diff);
                update(data, mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }
}
