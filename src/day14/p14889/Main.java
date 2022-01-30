package day14.p14889;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] players;
    static boolean[] visited;
    static int level;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day14/p14889/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        players = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        level = N / 2;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        dfs(1, 1);





    }

    public static void dfs(int node, int depth) {
        //1. 체크인
        //2. 목적지인가?
        //3. 인접한 곳
        //4. 갈 수 있는가?
        //5. 간다
        //6. 체크아웃

        visited[node] = true;
        if (depth == level) {
            return;
        }

        for (int i = 1; i <=N ; i++) {
            if (visited[i] == false) {
                dfs(i, depth + 1);
            }
        }

        visited[node] = false;
    }
}
