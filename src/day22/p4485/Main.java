package day22.p4485;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] moveX = {-1, 1, 0, 0};
    static final int[] moveY = {0, 0, -1, 1};

    static int N;
    static int[][] map;
    static int[][] result;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day22/p4485/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int problem = 0;
        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) {
                break;
            }

            problem++;
            map = new int[N][N];
            result = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    result[i][j] = Integer.MAX_VALUE;
                }
            }

            findPath();

            System.out.println("Problem " + problem + ": " + result[N - 1][N - 1]);
        }
    }

    public static void findPath() {

        //bfs
        /**
         * 1. 큐에서 뺌
         * 2. 목적지인가?
         * 3. 인접한 곳 검색
         * 4. 갈 수 있는가?
         * 5. 체크인
         * 6. 큐에 넣는다.
         * */

        Queue<Point> q = new LinkedList<>();
        result[0][0] = map[0][0];
        q.add(new Point(0, 0));

        while (! q.isEmpty()) {

            Point current = q.poll();
            int x = current.x;
            int y = current.y;
            
            //인접한 곳 선택
            for (int i = 0; i < 4; i++) {
                int newX = x + moveX[i];
                int newY = y + moveY[i];

                if (newX >= 0 && newX < N && newY >= 0 && newY < N) {
                    if (result[newX][newY] > result[x][y] + map[newX][newY]) {
                        result[newX][newY] = result[x][y] + map[newX][newY];
                        q.add(new Point(newX, newY));
                    }
                }
            }
        }
    }

}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}