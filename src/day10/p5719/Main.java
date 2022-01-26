package day10.p5719;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Edge>[] map;
    //static ArrayList<Edge>[] Tracking;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] distance;

    static int N, M, S, E;
    static StringBuilder sb = new StringBuilder();
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day10/p5719/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            map = new ArrayList[N + 1];
            distance = new int[N + 1];


            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= N; i++) {
                map[i] = new ArrayList<>();
            }

            //Init
            int U, V, P;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                U = Integer.parseInt(st.nextToken());
                V = Integer.parseInt(st.nextToken());
                P = Integer.parseInt(st.nextToken());

                map[U].add(new Edge(V, P));
            }

            //경로 찾기
            findShortestPath(S, E);

            //해당 경로 제외

            //다시 경로 찾기
        }
    }

    public static void findShortestPath(int start, int end){

        pq.add(new Edge(start, 0));
        Arrays.fill(distance, INF);
        distance[start] = 0;

        while (!pq.isEmpty()) {

            Edge current = pq.poll();

            if (distance[current.to] < current.weight) {
                continue;
            }

            for (Edge node : map[current.to]) {

                if (distance[node.to] > distance[current.to] + node.weight) {

                }

            }




        }
    }
}

class Edge implements Comparable<Edge> {
    int to;
    int weight;
    boolean isDeleted;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
        this.isDeleted = false;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}