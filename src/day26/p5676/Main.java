package day26.p5676;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, S = 1;
    static int[] numList;
    static long[] tree;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day26/p5676/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String input;

        while (true) {
             input = br.readLine();

            if (input == null) {
                break;
            }

            st = new StringTokenizer(input);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            while (S < N) {
                S *= 2;
            }

            numList = new int[N];
            tree = new long[S * 2];
            Arrays.fill(tree, 1);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numList[i] = Integer.parseInt(st.nextToken());
            }

            init();

            for (int i = 0; i < M; i++) {

                st = new StringTokenizer(br.readLine());
                String op = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (op.equals("P")) {
                    long result = query(1, S, 1, a, b);

                    if (result == 0) {
                        sb.append(0);
                    } else if (result < 0) {
                        sb.append("-");
                    } else {
                        sb.append("+");
                    }
                } else {
                    if (b > 0) {
                        b = 1;
                    } else if (b < 0) {
                        b = -1;
                    } else {
                        b = 0;
                    }
                    update(1, S, 1, a, b);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void init() {
        for (int i = 0; i < N; i++) {
            if (numList[i] < 0) {
                tree[S + i] = -1;
            } else if (numList[i] > 0) {
                tree[S + i] = 1;
            } else {
                tree[S + i] = 0;
            }
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] * tree[i * 2 + 1];
        }
    }

    public static long query(int left, int right, int node, int leftQuery, int rightQuery) {
        if (right < leftQuery || rightQuery < left) {
            return 1;
        } else if (leftQuery <= left && right <= rightQuery) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            long leftVal = query(left, mid, node * 2, leftQuery, rightQuery);
            long rightVal = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return leftVal * rightVal;
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
            long leftVal = update(left, mid, node * 2, target, value);
            long rightVal = update(mid + 1, right, node * 2 + 1, target, value);

            return tree[node] = leftVal * rightVal;
        }
    }
}
