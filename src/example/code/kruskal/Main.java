package example.code.kruskal;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static Edge[] edges;
    static int[] parent;
    static int answer;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/example/code/kruskal/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, weight);
        }

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        Arrays.sort(edges);

        int count = 0;
        while (true) {
            for (int i = 0; i < M; i++) {
                if (find(edges[i].from) != find(edges[i].to)) {
                    union(edges[i].from, edges[i].to);
                    answer += edges[i].weight;
                    count++;
                }
            }

            if (count == N - 1) {
                break;
            }
        }

        System.out.println(answer);
    }

    public static void union(int a, int b) {

        int aValue = find(a);
        int bValue = find(b);

        if (aValue > bValue) {
            parent[aValue] = bValue;
        } else if (aValue < bValue) {
            parent[bValue] = aValue;
        }
    }

    public static int find(int target) {
        if (target == parent[target]) {
            return target;
        }
        return parent[target] = find(parent[target]);
    }
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return Integer.compare(weight, e.weight);
    }
}
