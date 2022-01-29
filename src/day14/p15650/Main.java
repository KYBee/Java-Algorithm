package day14.p15650;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] numList;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day14/p15650/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numList = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            numList[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            dfs(i, "", 1, visited);
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int root, String word, int level, boolean[] visited) {

        //1. 체크인
        //2. 목적지인가?
        //3. 인접한 곳
        //4. 갈 수 있는가?
        //5. 간다
        //6. 체크아웃

        visited[root] = true;
        String newWord = word + numList[root] + " ";

        if (level == M) {
            sb.append(newWord + "\n");
            visited[root] = false;
            return;
        }

        for (int i = 1; i <= N; i++) {
            int next = numList[i];
            if (visited[i] == false && next >= numList[root] ) {
                dfs(i, newWord, level + 1, visited);
            }
        }

        visited[root] = false;
    }
}
