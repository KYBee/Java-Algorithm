package day12.p2667;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] moveX = {-1, 1, 0, 0};
    static final int[] moveY = {0, 0, -1, 1};

    static int N;
    static int[][] map;
    static int[][] visited;

    static int total = 0;
    static ArrayList<Integer> result;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day12/p2667/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        visited = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();

            for (int j = 1; j <= N; j++) {
                map[i][j] = temp.charAt(j - 1) - '0';
            }
        }


        result = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 1) {
                    int count = 0;
                    total += 1;
                    result.add(dfs(i, j, total, count));
                }
            }
        }


        System.out.println(total);

        Collections.sort(result);
        for (int num : result) {
            System.out.println(num);
        }

    }


    public static int dfs(int x, int y, int num, int count) {

        //1. 체크인
        //2. 목적지인가?
        //3. 인접한 곳으로 이동
        //4. 갈 수 있는가?
        //5. 간다
        //6. 체크아웃

        visited[x][y] = num;

        int newX, newY;
        for (int i = 0; i < 4; i++) {
            newX = x + moveX[i];
            newY = y + moveY[i];

            if (newX > 0 && newY > 0 && newX <= N && newY <= N) {
                if (visited[newX][newY] == 0 && map[newX][newY] != 0) {
                    count = dfs(newX, newY, num, count);
                }
            }
        }

        map[x][y] = 0;

        return count + 1;
    }
}
