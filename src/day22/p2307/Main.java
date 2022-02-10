package day22.p2307;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static final int INF = 1000000000;
    static int[][] path;
    static Edge[] edges;
    static List<Edge>[] graph;
    static int answer = -1;
    static int shortestTime = 0;
    static List<Edge> shortestPath = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day22/p2307/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, weight);
        }

        //맨 처음 다익스트라
        graph = new ArrayList[N + 1];
        for (int j = 1; j <= N; j++) {
            graph[j] = new ArrayList<>();
        }

        path = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            path[i][0] = INF;
            path[i][1] = i;
        }
        
        for (int j = 0; j < M; j++) {
            Edge current = edges[j];
            int from = current.from;
            int to = current.to;
            int weight = current.weight;

            graph[from].add(new Edge(from, to, weight));
            graph[to].add(new Edge(to, from, weight));
        }

        findPath(1);

        shortestTime = path[N][0];
        int t = N;
        int f = path[t][1];
        while (true) {
            shortestPath.add(new Edge(f, t, 0));
            if (f == 1) {
                break;
            } else {
                t = f;
                f = path[t][1];
            }
        }

        //shortestPath에서 한개씩 빼면서 진행
        for (int i = 0; i < shortestPath.size(); i++) {

            Edge deleted = shortestPath.get(i);

            for (int j = 1; j <= N; j++) {
                graph[j] = new ArrayList<>();
                path[j][0] = INF;
                path[j][1] = j;
            }

            for (int j = 0; j < M; j++) {
                Edge current = edges[j];
                if (deleted.from == current.from && deleted.to == current.to) {
                    continue;
                } else {
                    int from = current.from;
                    int to = current.to;
                    int weight = current.weight;

                    graph[from].add(new Edge(from, to, weight));
                    graph[to].add(new Edge(to, from, weight));
                }
            }

            //다익스트라 경로 찾기
            findPath(1);

            answer = Math.max(answer, path[N][0] - shortestTime);
            if (answer + shortestTime == INF) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(answer);
    }

    public static void findPath(int start) {

        /**
         * 1. 일단 pq에다가 넣고
         * 2. 뺀 다음 이미 방문한 곳인지 체크
         * 3. 주변 체크하고
         * 4. 업데이트 필요하면 업데이트
         * */

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        path[start][0] = 0;
        pq.add(new Edge(start, start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (current.weight > path[current.to][0]) {
                continue;
            }

            for (Edge e : graph[current.to]) {

                int cost = path[current.to][0] + e.weight;
                if (path[e.to][0] > cost) {
                    path[e.to][0] = cost;
                    path[e.to][1] = current.to;
                    pq.add(new Edge(current.to, e.to, cost));
                }
            }
        }
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
    public int compareTo(Edge e){
        return Integer.compare(weight, e.weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}