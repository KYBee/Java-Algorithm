package day08.p1922;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<Edge> edges = new LinkedList<>();
    static int[] vertexes;
    static int totalWeight = 0;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day08/p1922/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        int from, to, weight;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            edges.add(new Edge(from, to, weight));
        }
        vertexes = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            vertexes[i] = i;
        }

        //정렬
        Collections.sort(edges, Comparator.comparingInt(Edge::getWeight));


        int count = 0;
        for (Edge e : edges) {
            // 간선을 하나씩 뽑음
            // 해당 간선이 싸이클을 구성하면 넘어감
            // 해당 간선이 싸이클을 구성하지 않으면 Union을 함

            if (find(e.from) != find(e.to)) {
                count++;
                totalWeight += e.weight;

                union(e.from, e.to);
            }

            if (count >= N - 1) {
                break;
            }
        }

        System.out.println(totalWeight);

    }

    public static void union(int from, int to) {

        int fromVertex = find(from);
        int toVertex = find(to);

        vertexes[fromVertex] = toVertex;

    }

    public static int find(int target) {
        if (vertexes[target] == target) {
            return target;
        } else {
            return vertexes[target] = find(vertexes[target]);
        }
    }

}

class Edge {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }
}