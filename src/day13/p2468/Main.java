package day13.p2468;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] moveX = {-1, 1, 0, 0};
    static final int[] moveY = {0 ,0, -1, 1};

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int max = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day13/p2468/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 1; k <= 100; k++) {

            count = 0;

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] >= k) {
                        visited[i][j] = true;
                    }
                }
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (visited[i][j] == true) {
                        count += 1;
                        getSafetyRegion(visited, i, j);
                    }
                }
            }
            max = Math.max(max, count);
        }

        System.out.println(max);
    }

    public static void getSafetyRegion(boolean[][] visited, int x, int y) {

        //1. 체크인
        //2. 목적지인가?
        //3. 인접한 곳
        //4. 갈 수 있는가?
        //5. 간다
        //6. 체크아웃

        visited[x][y] = false;

        int newX, newY;
        for (int i = 0; i < 4; i++) {
            newX = x + moveX[i];
            newY = y + moveY[i];

            if(0 < newX && 0 < newY && newX <= N && newY <= N) {
                if(visited[newX][newY] == true) {
                    getSafetyRegion(visited, newX, newY);
                }
            }
        }
    }
}
