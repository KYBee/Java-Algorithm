package day09.p2606;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<Integer>[] computers;
    static boolean[] visited;
    static int total = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day09/p2606/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        computers = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        //init
        for (int i = 1; i <= N; i++) {
            computers[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            computers[from].add(to);
            computers[to].add(from);
        }

        DFS(1);
        System.out.println(total);
    }

    public static void DFS(int node) {
        //1. 체크인
        //2. 목적지인가?
        //3. 인접한 노드 확인
        //4. 갈 수 있는가?
        //5. 간다
        //6. 체크아웃
        visited[node] = true;

        for (int c : computers[node]) {
            if (visited[c] == false) {
                total++;
                DFS(c);
            }
        }
    }
}