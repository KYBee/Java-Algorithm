package day23.p15971.dijkstra;

import java.util.*;
import java.io.*;

public class Main {

    static final long INF = Long.MAX_VALUE;
    static int N, p1, p2;
    static List<Edge>[] map;
    static long[][] path;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day23/p15971/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());

        if (p1 == p2 || N == 2) {
            System.out.println(0);
            return;
        }

        path = new long[N + 1][2];
        map = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());

            map[from].add(new Edge(from, to, weight));
            map[to].add(new Edge(to, from, weight));
        }

        findPath(p1);

        //가장 긴 경로 하나를 제외함. 일단 모든 경로를 체크
        int to = N;
        int from = (int) path[to][1];
        long max = 0;

        while (true) {
            if (from == p1) {
                break;
            } else {
                max = Math.max(max, path[to][0] - path[from][0]);

                to = from;
                from = (int) path[to][1];
            }
        }

        System.out.println(path[p2][0] - max);
    }

    public static void findPath(int start) {

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            path[i][0] = INF;
            path[i][1] = i;
        }
        path[start][0] = 0;
        pq.add(new Edge(start, start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (current.weight > path[current.to][0]) {
                continue;
            }

            for (Edge next: map[current.to]) {
                long cost = path[current.to][0] + next.weight;

                if (path[next.to][0] > cost) {
                    path[next.to][0] = cost;
                    path[next.to][1] = current.to;
                    pq.add(new Edge(current.to, next.to, cost));
                }
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    long weight;

    Edge(int from, int to, long weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return Long.compare(weight, e.weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
