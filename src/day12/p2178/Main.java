package day12.p2178;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] moveX = {-1, 1, 0, 0};
    static final int[] moveY = {0, 0, -1, 1};

    static int N, M;
    static int[][] map;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day12/p2178/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();

            for (int j = 1; j <= M; j++) {
                map[i][j] = temp.charAt(j - 1) - '0';
            }
        }

        int answer = findShortestPath(1, 1, N, M);
        System.out.println(answer);
    }

    static int findShortestPath(int startX, int startY, int endX, int endY) {

        //1. 큐에서 하나를 꺼낸다.
        //2. 목적지인가?
        //3. 인접한 갈 수 있는 곳
        //4. 갈 수 있는가?
        //5. 체크인
        //6. 큐에 넣는다.

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY, 1));
        visited[startX][startY] = true;

        Point current;
        int newX, newY;
        while (!queue.isEmpty()){
            current = queue.poll();

            //목적지라면
            if (current.x == endX && current.y == endY) {
                return current.path;
            }

            for (int i = 0; i < 4; i++) {
                newX = current.x + moveX[i];
                newY = current.y + moveY[i];

                if (newX > 0 && newY > 0 && newX <= N && newY <= M) {
                    if (visited[newX][newY] == false && map[newX][newY] == 1) {
                        visited[newX][newY] = true;
                        queue.add(new Point(newX, newY, current.path + 1));
                    }
                }
            }
        }

        return 0;
    }
}

class Point {
    int x;
    int y;
    int path;

    public Point(int x, int y, int path) {
        this.x = x;
        this.y = y;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}