package day31.p2458;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static boolean map1[][];
    static boolean map2[][];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day31/p2458/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map1 = new boolean[N + 1][N + 1];
        map2 = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map1[from][to] = true;
            map2[to][from] = true;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i != j && map1[i][k] != false && map1[k][j] != false) {
                        map1[i][j] = true;
                    }
                    if (i != j && map2[i][k] != false && map2[k][j] != false) {
                        map2[i][j] = true;
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map1[i][j] = map1[i][j] | map2[i][j];
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            boolean isAble = true;
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }
                if (map1[i][j] == false) {
                    isAble = false;
                    break;
                }
            }

            if(isAble) answer++;
        }

        System.out.println(answer);
    }
}
