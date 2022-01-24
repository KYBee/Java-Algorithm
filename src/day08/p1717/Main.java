package day08.p1717;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] graph;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day08/p1717/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n + 1];

        //initialize
        for (int i = 0; i < graph.length; i++) {
            graph[i] = i;
        }

        int op, from, to;
        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());
            op = Integer.parseInt(st.nextToken());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());


            if (op == 0) {
                // Union
                union(from, to);
            } else {
                // Find
                int fromTarget = find(from);
                int toTarget = find(to);

                if (fromTarget == toTarget) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    public static void union(int from, int to) {
        int aTarget = find(from);
        int bTarget = find(to);

        graph[aTarget] = bTarget;
    }

    public static int find(int target) {

        if (graph[target] == target) {
            return target;
        } else {
            return graph[target] = find(graph[target]);
        }
    }


}
