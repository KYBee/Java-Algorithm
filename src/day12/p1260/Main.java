package day12.p1260;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, V;
    static List<Integer>[] graph;

    static boolean[] dfsVisited;
    static boolean[] bfsVisited;

    static StringBuilder sb = new StringBuilder();

    static List<Integer> dfsResult = new ArrayList<>();
    static List<Integer> bfsResult = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day12/p1260/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        dfsVisited = new boolean[N + 1];
        bfsVisited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        dfs(V);

        for (int i = 0; i < dfsResult.size() ; i++) {
            sb.append(dfsResult.get(i)).append(" ");
        }
        sb.append("\n");


        bfs(V);

        for (int i = 0; i < bfsResult.size() ; i++) {
            sb.append(bfsResult.get(i)).append(" ");
        }
        sb.append("\n");

        System.out.println(sb.toString());

    }


    static void dfs(int start) {

        // 1. 체크인
        // 2. 목적지인가?
        // 3. 인접한 곳 리스팅
        // 4. 갈 수 있는가?
        // 5. 간다
        // 6. 체크아웃

        dfsVisited[start] = true;

        dfsResult.add(start);

        for (int node : graph[start]) {
            if (dfsVisited[node] == false) {
                dfs(node);
            }
        }
    }

    static void bfs(int start) {

        Queue<Integer> queue = new LinkedList<>();

        //0. 초기화
        queue.add(start);
        bfsVisited[start] = true;

        while (!queue.isEmpty()) {
            //1. 큐에서 꺼내옴
            //2. 목적지인가
            //3. 연결된 곳에서 꺼내옴
            //4. 갈 수 있는가?
            //5. 체크인
            //6. 큐에 넣음

            int current = queue.poll();

            for (int node : graph[current]) {

                if (bfsVisited[node] == false) {

                    bfsVisited[node] = true;
                    queue.add(node);
                }
            }

            bfsResult.add(current);

        }
    }
}