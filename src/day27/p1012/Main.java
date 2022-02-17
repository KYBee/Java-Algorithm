package day27.p1012;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int TC;
    static int M, N, K;
    static int[][] map;

    static int answer;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day27/p1012/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());

        for (int i = 0; i < TC; i++) {
            st = new StringTokenizer(br.readLine());
            
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[M][N];
            answer = 0;

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[x][y] = 1;
            }

            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k] != 0) {
                        dfs(j, k);
                        answer += 1;
                    }
                }
            }

            System.out.println(answer);
        }
    }

    public static void dfs(int x, int y) {

        map[x][y] = 0;

        int newX, newY;
        for (int i = 0; i < 4; i++) {
            newX = x + dx[i];
            newY = y + dy[i];

            if (0 <= newX && newX < M && 0 <= newY && newY < N) {
                if (map[newX][newY] != 0) {
                    dfs(newX, newY);
                }
            }
        }
    }
}
