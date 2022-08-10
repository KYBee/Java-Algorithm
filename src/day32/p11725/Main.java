package day32.p11725;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] parent;

    static List<Integer>[] tree;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day32/p11725/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        tree = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        fillParent();

        for (int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }

    }

    public static void fillParent() {

        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : tree[current]) {
                if (parent[next] == 0) {
                    parent[next] = current;
                    q.add(next);
                }
            }
        }
    }
}
