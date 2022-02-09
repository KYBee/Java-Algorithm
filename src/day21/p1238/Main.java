package day21.p1238;

import java.util.*;
import java.io.*;

public class Main {

    static int V, E, X;
    static int[] path, reversePath;
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day21/p1238/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] map, reverseMap;
        path = new int[V + 1];
        reversePath = new int[V + 1];
        map = new ArrayList[V + 1];
        reverseMap = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            map[i] = new ArrayList<>();
            reverseMap[i] = new ArrayList<>();
        }
        
        //Init
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[from].add(new Edge(to, weight));
            reverseMap[to].add(new Edge(from ,weight));
        }

        findPath(X, map, path);
        findPath(X, reverseMap, reversePath);

        int max = -1;
        int[] result = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            result[i] = path[i] + reversePath[i];
            max = Math.max(max, result[i]);
        }

        System.out.println(max);
    }

    public static int[] findPath(int start, ArrayList<Edge>[] map, int[] result) {

        Arrays.fill(result, INF);
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        //1. 일단 넣는다.
        //2. 하나씩 빼고
        //3. 수정 필요한지 확인
        //4. 필요히면 다 뺀 다음 수정

        pq.add(new Edge(start, 0));
        result[start] = 0;

        while(!pq.isEmpty()) {
            Edge current = pq.poll();

            if (current.weight > result[current.to]) {
                continue;
            }

            for (Edge next : map[current.to]) {
                int cost = result[current.to] + next.weight;
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
