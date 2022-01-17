package day01.p3055;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;
import java.util.Arrays;

public class Main {

    //좌, 우, 위, 아래
    static final int[] MX = {-1, 1, 0, 0};
    static final int[] MY = {0, 0, -1, 1};

    static int R;
    static int C;
    static Queue<Point> queue = new LinkedList<>();
    static char[][] map1;
    static int[][] map2;

    static Point start;
    static Boolean foundAnswer = false;

    public static void bfs() {

        // 1. 큐에서 꺼내옴 -> dequeue -> S, *, ., D
        while (!queue.isEmpty()) {
            Point p = queue.poll();

            // 2. 목적지 인가 -> 비버의 굴인가 (D) 인가?
            // 비버의 집이면 종료
            if (map1[p.y][p.x] == 'D') {
                System.out.println(map2[p.y][p.x]);
                foundAnswer = true;
                break;
            }

            // 3. 연결된 곳을 순회 -> 좌 우 위 아래
            for (int i = 0; i < 4; i++) {

                int ty = p.y + MY[i];
                int tx = p.x + MX[i];

                // 4. 갈 수 있는가? -> 물 -> 맵을 벗어나지 않고, .,
                // 4. 갈 수 있는가? -> 고슴도치 -> 맵을 벗어나지 않고, ., D, 방문하지 않은 곳(map2가 0인곳)
                if (0 <= ty && ty < R && 0 <= tx && tx < C) {
                    if (p.type == '.' || p.type =='S') {
                        if((map1[ty][tx] == '.' || map1[ty][tx] == 'D') && map2[ty][tx] == 0) {
                            // 5. 체크인 -> 고슴도치 map2에 현재 + 1 을 기록함

                            map2[ty][tx] = map2[p.y][p.x] + 1;
                            queue.add(new Point(ty, tx, map1[ty][tx]));
                        }
                    } else if (p.type == '*') {
                        if(map1[ty][tx] == '.') {
                            // 5. 체크인 -> 물 map1에 기록
                            map1[ty][tx] = '*';
                            queue.add(new Point(ty, tx, '*'));
                        }
                    }
                }
            }
        }

    }


    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/day01/p3055/input2.txt"));
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();

        map1 = new char[R][C];
        map2 = new int[R][C];

        for (int i = 0; i < R; i++) {
            String line = sc.next();

            for (int j = 0; j < C; j++) {
                map1[i][j] = line.charAt(j);

                if (map1[i][j] == 'S') {
                    start = new Point(i, j, 'S');
                } else if (map1[i][j] == '*') {
                    queue.add(new Point(i, j, '*'));
                }
            }
        }

        //고습도치가 있는 위치를 최초에 큐에 넣음
        queue.add(start);

        bfs();

        if(!foundAnswer) {
            System.out.println("KAKTUS");
        }

    }
}


class Point {
    int y;
    int x;
    char type;

    Point(int y, int x, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public String toString() {
        return "[ " + x + " " + y + " " + type + " ]";
    }
}