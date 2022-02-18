package day31.p10159;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] graph;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day31/p10159/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = 1;
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(Arrays.toString(graph[i]));
        }

        System.out.println();

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i != j) {
                        if (graph[i][k] != 0 && graph[k][j] != 0){
                            graph[i][j] = graph[i][k] + graph[k][j];
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(Arrays.toString(graph[i]));
        }
        
    }
}
