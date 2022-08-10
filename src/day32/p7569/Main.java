package day32.p7569;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] dx = {-1, 1, 0, 0, 0, 0};
    static final int[] dy = {0, 0, -1, 1, 0, 0};
    static final int[] dz = {0, 0, 0, 0, -1, 1};

    static int N, M, H;
    static int[][][] box;
    static boolean fin;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day32/p7569/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][M][N];

        Queue<Point> q = new LinkedList<>();

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    box[k][i][j] = Integer.parseInt(st.nextToken());

                    if (box[k][i][j] == 1) {
                        q.add(new Point(j, i, k, 0));
                    }
                }
            }
        }

        // day 는 얼마나 걸리는지 //total 은 덜익은 토마토의 개수
        int day = 0;
        fin = true;

        while (!q.isEmpty()) {

            fin = false;
            Point current = q.poll();

            int x = current.x;
            int y = current.y;
            int z = current.z;

            for (int i = 0; i < 6; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                int newZ = z + dz[i];

                if (0 <= newX && newX < N && 0 <= newY && newY < M && 0 <= newZ && newZ < H) {
                    if (box[newZ][newY][newX] == 0) {
                        q.add(new Point(newX, newY, newZ, current.day + 1));
                        box[newZ][newY][newX] = 1;
                        day = Math.max(day, current.day + 1);
                    }
                }
            }
        }


        for (int k = 0; k < H; k++) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (box[k][i][j] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(day);
    }
}

class Point {
    int x;
    int y;
    int z;
    int day;


    Point (int x, int y, int z, int day) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.day = day;
    }
}

