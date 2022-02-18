package others.최소신장트리;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M;

    static Edge[] edge1;
    static int[] parent;

    static List<Edge>[] edge2;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static int kruskalAnswer = 0;
    static int primAnswer = 0;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/others/최소신장트리/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edge1 = new Edge[M];
        parent = new int[N + 1];

        edge2 = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            edge2[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edge1[i] = new Edge(from ,to ,weight);

            edge2[from].add(new Edge(from, to, weight));
            edge2[to].add(new Edge(to, from, weight));
        }

        //Kruskal

        Arrays.sort(edge1);
        int count = 0;

        for (int i = 0; i < M; i++) {
            Edge current = edge1[i];

            if (find(current.from) == find(current.to)) {
                continue;
            } else {
                union(current.from, current.to);
                count++;
                kruskalAnswer += current.weight;

                if (count >= N - 1) {
                    break;
                }
            }
        }

        System.out.println(kruskalAnswer);

        //Prim

        count = 0;
        pq.add(new Edge(1, 1,0));
        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.to] == false) {
                visited[current.to] = true;
                primAnswer += current.weight;
                count++;

                if (count == N) {
                    break;
                }

                for (Edge e : edge2[current.to]) {
                    if (visited[e.to] == false) {
                        pq.add(e);
                    }
                }
            }
        }

        System.out.println(primAnswer);
    }

    public static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent > bParent) {
            parent[aParent] = bParent;
        } else if (aParent < bParent) {
            parent[bParent] = aParent;
        }
    }

    public static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;

    Edge (int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return Integer.compare(weight, e.weight);
    }
}
