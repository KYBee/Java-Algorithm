package day09.p1753;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int V, E;
    static int start;
    static int[] result;

    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static List<Edge> [] edges;


    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day09/p1753/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());

        edges = new ArrayList[V + 1];
        result = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            edges[i]  = new ArrayList<>();
            result[i] = INF;
        }

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[from].add(new Edge(to, weight));
        }


        //init
        result[start] = 0;
        pq.add(new Edge(start, 0));

        findPath();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V ; i++) {
            if (result[i] != INF) {
                sb.append(result[i]).append("\n");
            } else {
                sb.append("INF\n");
            }
        }

        System.out.println(sb.toString());

    }

    public static void findPath() {

        //큐가 빌 때 까지
        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            // 만약 지금 거리가 더 작으면 다 갱신해주고 큐에 넣어줌
            if (current.weight <= result[current.to]) {

                for (Edge e : edges[current.to]) {

                    if (result[current.to] + e.weight < result[e.to]) {
                        result[e.to] = result[current.to] + e.weight;
                        pq.add(new Edge(e.to, result[e.to]));

                    }

                }
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }
}