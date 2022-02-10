package example.code.union.find;

import java.util.*;
import java.io.*;

public class Main {

    static int V, E;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/example/code/union/find/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        System.out.println(Arrays.toString(parent));

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());


            //싸이클이 발생할 경우 종료
            if (find(from) == find(to)) {
                return;
            } else {
                union(from, to);
            }
        }

        System.out.println(Arrays.toString(parent));

    }

    public static void union(int from, int to) {

        int fromParent = find(from);
        int toParent = find(to);

        if (fromParent < toParent) {
            parent[toParent] = fromParent;
        } else {
            parent[fromParent] = toParent;
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
