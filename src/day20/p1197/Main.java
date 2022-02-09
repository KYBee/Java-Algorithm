package day20.p1197;

import java.util.*;
import java.io.*;

public class Main {

    static int V, E;
    static int edges = 0;
    static int[] map;
    static int weights = 0;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day20/p1197/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        map = new int[V + 1];

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());

            pq.add(new Edge(from, to, weight));
        }

        for (int i = 1; i <= V; i++) {
            map[i] = i;
        }

        while (edges < V - 1) {
            Edge current = pq.poll();

            int from = current.from;
            int to = current.to;

            if (find(from) != find(to)) {
                edges++;
                union(from, to);
                weights += current.weight;
            }
        }

        System.out.println(weights);

    }

    public static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent < bParent) {
            map[aParent] = bParent;
        } else {
            map[bParent] = map[aParent];
        }
    }

    public static int find(int a) {
        if (map[a] == a) {
            return a;
        } else {
            return map[a] = find(map[a]);
        }
    }
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    long weight;

    public Edge(int from, int to, long weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int compareTo(Edge o){
        return Long.compare(weight, o.weight);
    }

    public String toString() {
        return from + " " + to + " " + weight;
    }

}