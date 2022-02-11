package day24.p6497;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static List<Edge>[] graph;
    static boolean[] visited;

    static long total;
    static long shortest;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day24/p6497/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            total = 0;
            shortest = 0;

            if (N == 0 && M == 0) {
                return;
            }

            graph = new ArrayList[N];
            visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                long weight = Long.parseLong(st.nextToken());

                graph[from].add(new Edge(to, weight));
                graph[to].add(new Edge(from, weight));
                total += weight;
            }

            findPath(0);

            System.out.println(total - shortest);
        }
    }

    public static void findPath(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            /**
             * Prim
             * 1. 일단 첫 스타팅 포인트를 큐에 넣고
             * 2. 큐에서 하나를 뽑고
             * 3. 방문했었는지 확인
             * 4. 체크인
             * 5. 인접한거 확인
             * 6. 갈 수 있는가?
             * 7. 큐에 넣는다.
             * */

            Edge current = pq.poll();

            int to = current.to;
            long weight = current.weight;

            if (visited[to]) {
                continue;
            }

            shortest += weight;
            visited[to] = true;

            for (Edge e : graph[to]) {
                pq.add(e);
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int to;
    long weight;

    Edge (int to, long weight) {
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
                "to=" + to +
                ", weight=" + weight +
                '}';
    }
}
