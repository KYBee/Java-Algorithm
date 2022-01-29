package day14.p15651;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] numList;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day14/p15651/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numList = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            numList[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            dfs(i, "", 1);
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int root, String word, int level) {

        //1. 체크인
        //2. 목적지인가?
        //3. 인접한 곳
        //4. 갈 수 있는가?
        //5. 간다
        //6. 체크아웃

        String newWord = word + numList[root] + " ";

        if (level == M) {
            sb.append(newWord + "\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            dfs(i, newWord, level + 1);
        }
    }
}
