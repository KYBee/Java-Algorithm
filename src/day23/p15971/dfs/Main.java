package day23.p15971.dfs;

import java.util.*;
import java.io.*;

public class Main {

    static int N, p1, p2;
    static List<Edge>[] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day23/p15971/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        map = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[from].add(new Edge(from, to, weight));
            map[to].add(new Edge(to, from, weight));
        }

        findPath(p1, 0, 0);
    }

    public static void findPath(int start, int d, int maxNum) {

        //dfs
        /**
         * 1. 체크인
         * 2. 목적지인가?
         * 3. 인접 노드
         * 4. 갈 수 있는가?
         * 5. 간다.
         * 6. 체크아웃
         * */

        visited[start] = true;

        if (start == p2) {
            System.out.println(d - maxNum);
        }

        for (Edge e : map[start]) {
            if (!visited[e.to]) {
                findPath(e.to, d + e.weight, Math.max(maxNum, e.weight));
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;

    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return Integer.compare(weight, e.weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
