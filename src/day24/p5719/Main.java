package day24.p5719;

import java.io.*;
import java.util.*;

public class Main {

    /**
     * N : 장소 수
     * M : 도로 수
     * S : 시작점
     * D : 도착점
     * */
    static final long INF = Long.MAX_VALUE;
    static int N, M;
    static int S, D;

    static List<Edge>[] map;
    static List<Integer>[] tracking;

    static boolean[][] isShortest;
    static long[] path;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day24/p5719/input.txt"));
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

            map = new ArrayList[N];
            tracking = new ArrayList[N];

            path = new long[N];
            isShortest = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                map[i] = new ArrayList<>();
            }
            
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                long weight = Long.parseLong(st.nextToken());

                map[from].add(new Edge(to, weight));
            }

            findPath(S);

            //최단 경로가 없는 경우
            if (path[D] == INF) {
                sb.append("-1\n");
                continue;
            }

            //path를 저장한 뒤에 해당 패스를 deleted 처리 한다. path 저장은 backTracking
            findShortestEdge(D, S);
            findPath(S);

            if (path[D] == INF) {
                sb.append("-1\n");
            } else {
                sb.append(path[D] + "\n");
            }
        }
        System.out.print(sb);
    }

    public static void findShortestEdge(int now, int end) {

        if (now == end) {
            return;
        }

        for (int next : tracking[now]) {
            if (isShortest[next][now] == false) {
                isShortest[next][now] = true;
                findShortestEdge(next, end);
            }
        }

    }

    public static void findPath(int start) {

        for (int i = 0; i < N; i++) {
            tracking[i] = new ArrayList<>();
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        Arrays.fill(path, INF);
        path[start] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (current.weight > path[current.to]) {
                continue;
            }

            for (Edge next : map[current.to]) {
                //최단 경로이면 넘어간다.
                if (isShortest[current.to][next.to]) {
                    continue;
                }

                long cost = path[current.to] + next.weight;

                if (path[next.to] > cost) {
                    tracking[next.to].clear();
                    tracking[next.to].add(current.to);
                    path[next.to] = cost;
                    pq.add(new Edge(next.to, cost));
                }
                //같으면 추가해줌
                if (path[next.to] == cost) {
                    tracking[next.to].add(current.to);
                }
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
    public int compareTo(Edge e){
        return Long.compare(weight, e.weight);
    }
}