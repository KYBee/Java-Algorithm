package day12.p2644;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int p1, p2;

    static List<Integer>[] family;
    static int[] visited;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day12/p2644/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        family = new ArrayList[N + 1];
        visited = new int[N + 1];
        Arrays.fill(visited, -1);

        for (int i = 1; i <= N; i++) {
            family[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            family[from].add(to);
            family[to].add(from);
        }

        bfs(p1, p2);

        System.out.println(visited[p2]);

    }

    public static void bfs(int p1, int p2) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(p1);
        visited[p1] = 0;
        int total = 0;

        while (!queue.isEmpty()) {
            //1. queue 에서 뺌
            //2. 목적지 인지 확인
            //3. 인접한 애들 검색
            //4. 갈 수 있는가?
            //5. 갈 수 있으면 큐에다 넣기
            //6. 체크인
            //7. 큐에서 하나를 뺀다

            int current = queue.poll();

            if (current == p2) {
                visited[p2] = visited[current];
            }

            for (int node : family[current]) {
                if (visited[node] == -1) {
                    visited[node] = visited[current] + 1;
                    queue.add(node);
                }
            }
        }
    }

}
