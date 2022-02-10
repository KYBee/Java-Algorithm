package day22.p1504;

import java.util.*;
import java.io.*;

public class Main {

    static final long INF = Long.MAX_VALUE;
    static int N, E;
    static int p1, p2;
    static List<Edge>[] graph;
    static long[] path1;
    static long[] path2;
    static long[] path3;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day22/p1504/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());


        graph = new ArrayList[N + 1];
        path1 = new long[N + 1];
        path2 = new long[N + 1];
        path3 = new long[N + 1];

        Arrays.fill(path1, INF);
        Arrays.fill(path2, INF);
        Arrays.fill(path3, INF);

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            Long weight = Long.parseLong(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());


        //다익스트라 -> 1 에서 p1, p2, N까지
        findPath(path1, 1);
        findPath(path2, p1);
        findPath(path3, p2);

        if (path1[p1] == INF || path2[p2] == INF || path3[N] == INF) {
            System.out.println(-1);
        } else if (path1[p2] == INF || path3[p1] == INF || path2[N] == INF) {
            System.out.println(-1);
        } else {
            long totalWeight1 = path1[p1] + path2[p2] + path3[N];
            long totalWeight2 = path1[p2] + path3[p1] + path2[N];
            System.out.println(Math.min(totalWeight1, totalWeight2));
        }
    }

    public static void findPath(long[] path, int start) {

        /**
         * 1. 일단 큐에 시작점을 넣음
         * 2. 큐가 빌 동안
         * 3. 인접한 노드까지의 거리를 하나씩 뺀 다음
         * 4. 수정이 필요하면 수정
         * 5. 다시 큐에 넣기
         * */

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        path[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (current.weight > path[current.to]) {
                continue;
            }

            for (Edge e : graph[current.to]) {

                long cost = current.weight + e.weight;
                if (path[e.to] > cost) {
                    path[e.to] = cost;
                    pq.add(new Edge(e.to, cost));
                }
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int to;
    long weight;

    Edge(int to, long weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return Long.compare(weight, e.weight);
    }
}