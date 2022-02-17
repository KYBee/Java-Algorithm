package day26.p16975;

import java.util.*;
import java.io.*;

public class Main {

    //TODO: 미완성

    static int N, M, S = 1;
    static int[] numList;
    static int[] tree;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day26/p16975/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        while (S < N) {
            S *= 2;
        }

        numList = new int[N];
        tree = new int[S * 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        init();

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());
            if (op == 1) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                query(from, to, value);
            } else {
                int target = Integer.parseInt(st.nextToken());
                sb.append(tree[S + target - 1]).append("\n");
            }
        }

        System.out.print(sb);
    }

    public static void init() {
        for (int i = 0; i < N; i++) {
            tree[S + i] = numList[i];
        }
        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public static void query(int leftQuery, int rightQuery, int value) {
        
        int left = S + leftQuery - 1;
        int right = S + rightQuery - 1;
        for (int i = left; i <= right ; i++) {
            tree[i] += value;
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }
}
