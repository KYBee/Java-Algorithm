package example.code.dijkstra;

import java.util.*;
import java.io.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int V, E;
    static int start;
    static int[] path;
    static ArrayList<Edge>[] map;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/example/code/dijkstra/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        path = new int[V + 1];
        map = new ArrayList[V + 1];

        start = Integer.parseInt(br.readLine());

        for (int i = 1; i <= V; i++) {
            path[i] = INF;
            map[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[from].add(new Edge(to, weight));
        }

        findPath(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V ; i++) {
            if (path[i] != INF) {
                sb.append(path[i]).append("\n");
            } else {
                sb.append("INF\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static void findPath(int start) {
        //TODO
        //1. 큐에 시작점을 넣음
        //2. 큐를 하나하나 돌면서 가장 작은걸 빼고
        //3. 업데이트할 필요가 있는지 확인 -> 필요없으면 continue
        //4. 수정해야하면 수정을 함
        //   4-1. 일단 to 에 붙어있는 목적지들을 확인하고
        //   4-2. 더 작으면 업데이트
        //5. pq가 Empty일 때 까지 반복

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        //Queue에 시작점을 넣음
        pq.add(new Edge(start, 0));
        path[start] = 0;

        while (!pq.isEmpty()) {
            //가장 작은 것을 뺌
            Edge current = pq.poll();

            //업데이트 할 필요가 있는지 확인 -> 필요없으면 continue
            if (current.weight > path[current.to]) {
                continue;
            }

            //수정해야하면 수정을 함
            for (Edge next : map[current.to]) {

                //일단 to 에 붙어있는 목적지들을 확인하고 목적지를 거쳐서 nextweight까지 가는 것이 현재 Path 보다 작으면
                int cost = current.weight + next.weight;

                //수정
                if (current.weight + next.weight < path[next.to]) {
                    path[next.to] = cost;
                    pq.add(new Edge(next.to,  cost));
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

    @Override
    public int compareTo(Edge e) {
        return Integer.compare(weight, e.weight);
    }

    @Override
    public String toString() {
        return to + " " + weight;
    }
}