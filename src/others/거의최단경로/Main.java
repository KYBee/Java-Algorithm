package others.거의최단경로;

import java.util.*;
import java.io.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;

    static int N, M;
    static int S, D;
    static List<Edge>[] graph;
    static int[] path;
    static boolean[][] isDeleted;

    static List<Integer>[] shortest;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/others/거의최단경로/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            graph = new ArrayList[N];
            shortest = new ArrayList[N];
            isDeleted = new boolean[N][N];
            path = new int[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
                shortest[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                graph[from].add(new Edge(to, weight));
            }

            getShortestPath(S);
            deleteShortestPath(D);
            getShortestPath(S);

            if (path[D] == INF) {
                sb.append("-1\n");
            } else {
                sb.append(path[D] + "\n");
            }
        }

        System.out.println(sb);
    }

    public static void getShortestPath(int start) {

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(path, INF);
        path[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            //업데이트 필요한지 확인
            if (path[current.to] < current.weight) {
                continue;
            }

            for (Edge next : graph[current.to]) {

                if (isDeleted[current.to][next.to]) {
                    continue;
                }

                int cost = path[current.to] + next.weight;
                if (path[next.to] > cost) {
                    shortest[next.to].clear();
                    shortest[next.to].add(current.to);

                    path[next.to] = cost;
                    pq.add(new Edge(next.to, cost));
                } else if (path[next.to] == cost) {
                    shortest[next.to].add(current.to);
                }
            }
        }
    }
    
    public static void deleteShortestPath(int destination) {
        for (int start : shortest[destination]) {
            if (isDeleted[start][destination] == false) {
                isDeleted[start][destination] = true;
                deleteShortestPath(start);
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

    @Override
    public int compareTo(Edge e){
        return Integer.compare(weight, e.weight);
    }
}