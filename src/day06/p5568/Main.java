package day06.p5568;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int[] card;
    static int N, K;
    static boolean[] visited;
    static Set<String> result = new HashSet<>();

    public static void dfs(int target, int length, String str, boolean[] visited) {
        //1. 체크인
        int current = card[target];
        visited[target] = true;


        //2. 목적지인가 -> 마지막 노드인가 && 이전에 없었던 노드인가
        if (length == K) {
            result.add(str + current);
            visited[target] = false;
            return;
        }

        //3. 인접한 노드들 검색
        for (int i = 0; i < N; i++) {
            if (i == target || visited[i] == true) {
                continue;
            } else {
                //4. 이동가능한가
                //5. 간다
                dfs(i, length + 1, str + current, visited);
            }
        }

        //6. 체크아웃
        visited[target] = false;

    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day06/p5568/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        card = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            card[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < N; i++) {
            dfs(i, 1, "", visited);
        }

        System.out.println(result.size());
    }
}