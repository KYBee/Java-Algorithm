package day23.p1647;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static Edge[] edges;
    static int[] parent;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day23/p1647/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        edges = new Edge[M];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from ,to, weight);
        }

        Arrays.sort(edges);

        int count = 0;
        int index = 0;
        int max = 0;
        while (true) {

            Edge current = edges[index++];
            int from = current.from;
            int to = current.to;
            int weight = current.weight;

            if (find(from) != find(to)) {
                union(from, to);
                max = Math.max(max, weight);
                answer += weight;
                count++;
            }

            if (count == N - 1) {
                break;
            }
        }

        System.out.println(answer - max);
    }

    public static void union(int from, int to) {
        int fromVal = find(from);
        int toVal = find(to);

        if (fromVal > toVal) {
            parent[fromVal] = toVal;
        } else if (fromVal < toVal) {
            parent[toVal] = fromVal;
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
    int from, to, weight;

    Edge (int from, int to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return Integer.compare(weight, e.weight);
    }

}
