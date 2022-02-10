package others.p262.전보;

import sun.security.krb5.internal.APOptions;

import java.util.*;
import java.io.*;

public class Main {

    static int V, E, S;
    static final long INF = Long.MAX_VALUE;
    static long[] path;
    static ArrayList<Edge>[] edge;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/others/p262/전보/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        path = new long[V + 1];
        edge = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            edge[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());

            edge[from].add(new Edge(to, weight));
            edge[to].add(new Edge(from, weight));
        }

        System.out.println(Arrays.toString(path));
        findPath(S);
        System.out.println(Arrays.toString(path));

    }

    public static void findPath(int start) {
        /**
         * 1. 일단 첫 부분을 큐에 넣음
         * 2. 하나씩 빼냄
         * 3. 만약 수정이 필요하면 수정함
         * 4. 이후에 조치를 취함
         * */

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        Arrays.fill(path, INF);

        while (!pq.isEmpty()) {

            Edge current = pq.poll();

            if (current.weight > path[current.to]) {
                continue;
            }

            for (Edge e : edge[current.to]) {
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
