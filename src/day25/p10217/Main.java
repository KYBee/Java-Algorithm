package day25.p10217;

import java.util.*;
import java.io.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int answer = INF;
    static int TC;
    static int N, M, K;

    static int[][] path;
    static List<Edge>[] map;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day25/p10217/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        TC = Integer.parseInt(br.readLine());

        for (int i = 0; i < TC; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
        
            path = new int[N + 1][M + 1];
            map = new ArrayList[N + 1];
            answer = INF;

            for (int j = 1; j <= N; j++) {
                map[j] = new ArrayList<>();
                Arrays.fill(path[j], INF);
            }

            for (int j = 1; j <= K; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                map[from].add(new Edge(to, weight, time));
            }

            answer = findPath();

            if (answer == INF) {
                sb.append("Poor KCM\n");
            } else {
                sb.append(answer + "\n");
            }

        }

        System.out.println(sb.toString());
    }

    public static int findPath() {

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        path[1][0] = 0;
        pq.add(new Edge(1, 0, 0));

        while (!pq.isEmpty()) {
            
            Edge current = pq.poll();

            if (current.to == N) {
                answer = Math.min(answer, current.time);
                return answer;
            }

            for (Edge next : map[current.to]) {
                int cost = current.weight + next.weight;

                if (cost > M) {
                    continue;
                }

                int time = current.time + next.time;
                if (path[next.to][cost] > time) {
                    path[next.to][cost] = time;
                    pq.add(new Edge(next.to, cost, time));
                }
            }
        }
        return answer;
    }
}

class Edge implements Comparable<Edge> {
    int to, weight, time;

    public Edge(int to, int weight, int time) {
        this.to = to;
        this.weight = weight;
        this.time = time;
    }

    @Override
    public int compareTo(Edge e) {
        int comp = Integer.compare(time, e.time);
        if(comp == 0) {
            return Integer.compare(weight, e.weight);
        }
        return comp;
    }
}

