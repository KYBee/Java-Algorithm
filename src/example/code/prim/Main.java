package example.code.prim;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static List<Edge>[] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/example/code/prim/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[from].add(new Edge(to, weight));
            map[to].add(new Edge(from, weight));
        }



        pq.add(new Edge(1, 0));

        int count = 0;
        boolean[] visited = new boolean[N + 1];

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int to = current.to;

            if (visited[to]) {
                continue;
            }

            visited[to] = true;
            answer += current.weight;
            count++;

            if (count == N) {
                break;
            } else {
                for (Edge e : map[to]) {
                    pq.add(e);
                }
            }
        }

        System.out.println(answer);
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
