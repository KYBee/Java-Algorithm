package day21.p10282;

import java.util.*;
import java.io.*;

public class Main {

    static int T, V, E, S;
    static final int INF = Integer.MAX_VALUE;
    static int count, max;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day21/p10282/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            count = 0;
            max = 0;
            
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            int[] result;
            ArrayList<Edge>[] map = new ArrayList[V + 1];

            for (int j = 1; j <= V; j++) {
                map[j] = new ArrayList<>();
            }

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                //양방향 그래프니까. 서로 의존 가능
                map[to].add(new Edge(from, weight));
            }

            result = findPath(S, map, V);

            for (int j = 1; j <= V; j++) {
                if (result[j] != INF) {
                    count++;
                    max = Math.max(max, result[j]);
                }
            }

            sb.append(count + " " + max + "\n");
        }

        System.out.println(sb.toString());
    }

    public static int[] findPath(int S, ArrayList<Edge>[] map, int V) {
        /**
         * 1. 큐에 시작점을 넣음
         * 2. 큐에서 하나씩 뺌
         * 3. 이미 들린곳인지 확인
         * 4. 수정이 필요하면 수정함
         * */

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] result = new int[V + 1];
        Arrays.fill(result, INF);
        result[S] = 0;
        pq.add(new Edge(S, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (current.weight > result[current.to]) {
                continue;
            }

            for (Edge next : map[current.to]) {
                int cost = current.weight + next.weight;

                if (result[next.to] > cost) {
                    result[next.to] = cost;
                    pq.add(new Edge(next.to, cost));
                }
            }
        }

        return result;
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
    public int compareTo(Edge e) {
        return Integer.compare(weight, e.weight);
    }
}
