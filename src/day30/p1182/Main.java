package day30.p1182;

import java.util.*;
import java.io.*;

public class Main {

    static int N, S;
    static int[] numList;

    static Set<String> result = new HashSet<>();
    static int answer = 0;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day30/p1182/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        numList = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        // 공집합 처리
        if (S == 0) {
            System.out.println(answer - 1);
        } else {
            System.out.println(answer);
        }
    }

    public static void dfs (int n , int sum) {

        if (n == N) {
            if (sum == S) {
                answer++;
            }
            return;
        }

        dfs(n + 1, sum + numList[n]);
        dfs(n + 1, sum);
    }
}
