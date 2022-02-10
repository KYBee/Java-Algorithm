package day22.p20040;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] parent;
    static int round = 0;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day22/p20040/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (find(from) == find(to)) {
                round = i + 1;
                break;
            } else {
                union(from, to);
            }
        }

        System.out.println(round);
    }

    public static void union(int from, int to) {

        int fromValue = find(from);
        int toValue = find(to);

        if (fromValue > toValue) {
            parent[fromValue] = toValue;
        } else {
            parent[toValue] = fromValue;
        }

    }

    public static int find(int target) {
        if (target == parent[target]) {
            return target;
        } else {
            return parent[target] = find(parent[target]);
        }

    }
}
