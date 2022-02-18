package day31.p2206;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day31/p2206/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[2][N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }

    public static int bfs(int x, int y) {

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 1, false));

        visited[0][x][y] = true;
        visited[1][x][y] = true;

        while (!q.isEmpty()) {
            Point current = q.poll();
            x = current.x;
            y = current.y;

            if (current.x == N - 1 && current.y == M - 1) {
                return current.dis;
            }

            for (int i = 0; i < 4; i++) {

                int newX = x + dx[i];
                int newY = y + dy[i];

                if (0 <= newX && newX < N && 0 <= newY && newY < M) {
                    if (current.drill) {
                        // 이미 한번 부셨으면
                        if (map[newX][newY] == 0 && visited[1][newX][newY] == false) {
                            visited[1][newX][newY] = true;
                            q.add(new Point(newX, newY, current.dis + 1, true));
                        }
                    } else {
                        // 안부셨으면
                        // 벽을 만났을 때
                        if (map[newX][newY] == 0 && visited[0][newX][newY] == false) {
                            visited[0][newX][newY] = true;
                            q.add(new Point(newX, newY, current.dis + 1, false));
                        } else if (map[newX][newY] == 1 && visited[1][newX][newY] == false) {
                            visited[1][newX][newY] = true;
                            q.add(new Point(newX, newY, current.dis + 1, true));
                        }
                    }
                }
            }
        }

        return -1;
    }
}

class Point {
    int x;
    int y;
    int dis;
    boolean drill;

    Point(int x, int y, int dis, boolean drill) {
        this.x = x;
        this.y = y;
        this.dis = dis;
        this.drill = drill;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", dis=" + dis +
                ", drill=" + drill +
                '}';
    }
}