package day32.p7562;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};
    static final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    static int TC;
    static int N;

    static int[] start = new int[2];
    static int[] end = new int[2];

    static int answer;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day32/p7562/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        TC = Integer.parseInt(br.readLine());

        for (int i = 0; i < TC; i++) {
            answer = 0;

            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());

            System.out.println(bfs(start[0], start[1], 0));
        }
    }

    public static int bfs(int x, int y, int depth) {

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 0));
        boolean[][] visited = new boolean[N][N];
        visited[x][y] = true;

        while (! q.isEmpty()) {
            Point current = q.poll();

            if (current.x == end[0] && current.y == end[1]) {
                return current.depth;
            }

            x = current.x;
            y = current.y;

            for (int i = 0; i < 8; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (0 <= newX && newX < N && 0 <= newY && newY < N) {
                    if (!visited[newX][newY]){
                        q.add(new Point(newX, newY, current.depth + 1));
                        visited[newX][newY] = true;
                    }
                }
            }
        }

        return 0;
    }
}

class Point {
    int x, y;
    int depth;

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", depth=" + depth +
                '}';
    }

    Point(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
}