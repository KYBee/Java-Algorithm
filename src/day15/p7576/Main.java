package day15.p7576;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] moveX = {-1, 1, 0, 0};
    static final int[] moveY = {0, 0, -1, 1};

    static int count = 0;
    static int N, M;
    static int[][] visited;

    static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day15/p7576/input4.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                visited[i][j] = Integer.parseInt(st.nextToken());
                if (visited[i][j] == 0) {
                    count++;
                }
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visited[i][j] == 1) {
                    queue.add(new Point(i, j));
                }
            }
        }

        bfs();

        if (count != 0) {
            System.out.println(-1);
        } else {
            int maxNum = -1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    maxNum = Math.max(maxNum, visited[i][j]);
                }
            }

            System.out.println(--maxNum);
        }
    }

    public static void bfs() {

        //1. 큐에서 꺼낸다.
        //2. 목적지인가?
        //3. 인접한 곳
        //4. 갈 수 있는가?
        //5. 체크인
        //6. 큐에 넣는다.


        while (!queue.isEmpty()) {

            Point current = queue.poll();

            for (int k = 0; k < 4; k++) {

                int newX = current.x + moveX[k];
                int newY = current.y + moveY[k];


                if (0 <= newX && newX < N && 0 <= newY && newY < M) {
                    if (visited[newX][newY] == 0) {
                        queue.add(new Point(newX, newY));
                        visited[newX][newY] = visited[current.x][current.y] + 1;
                        count--;
                    }
                }
            }
        }
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}