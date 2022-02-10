package example.code.bellman.ford;

import java.util.*;
import java.io.*;

public class Main {

    static final long INF = Long.MAX_VALUE;
    static int V, E;
    static long[] result, newResult;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/example/code/bellman/ford/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        result = new long[V + 1];
        Arrays.fill(result, INF);
        edges = new Edge[E];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());

            edges[i] = new Edge(from, to, weight);
        }

        StringBuilder sb = new StringBuilder();
        getPath(1);
        if (isNegativeCycle(1)) {
            sb.append(-1 +"\n");
        } else {
            for (int i = 2; i <= V; i++) {
                if (result[i] == INF) {
                    sb.append("-1\n");
                } else {
                    sb.append(result[i] + "\n");
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static void getPath(int start) {
        /**
         * 1. 목적지를 0으로 두고
         * 2. 모든 edge들을 돌면서 초기화 함 -> 현재까지의 경로상 INF가 아닐때만
         * 3. 이걸 V - 1번 반복
         * */
        result[start] = 0;
        for (int i = 1; i < V; i++) {
            for (Edge next : edges) {
                if (result[next.from] != INF) {
                    if (result[next.to] > result[next.from] + next.weight) {
                        result[next.to] = result[next.from] + next.weight;
                    }
                }
            }
        }
    }

    public static boolean isNegativeCycle(int start) {

        for (Edge next : edges) {
            if (result[next.from] != INF) {
                if (result[next.to] > result[next.from] + next.weight) {
                    return true;
                }
            }
        }

        return false;
    }

}

class Edge {
    int from;
    int to;
    long weight;

    public Edge(int from, int to, long weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
