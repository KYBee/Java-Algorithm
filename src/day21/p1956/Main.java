package day21.p1956;

import java.util.*;
import java.io.*;


public class Main {

    static int V, E;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day21/p1956/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        map = new int[V + 1][V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[from][to] = weight;
        }

        /**
         * 1. 일단 경로가 있는지를 확인
         * 2. 경로가 있는데 아직 반영이 안됏거나 더 작으면 반영
         * */
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (map[i][k] != 0 && map[k][j] != 0) {
                        if (map[i][j] == 0 || map[i][j] > map[i][k] + map[k][j]){
                            map[i][j] = map[i][k] + map[k][j];
                        }
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        boolean flag = false;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (map[i][j] != 0 && map[j][i] != 0) {
                    min = Math.min(map[i][j] + map[j][i], min);
                    if (!flag) {
                        flag = true;
                    }
                }
            }
        }

        if(flag) {
            System.out.println(min);
        } else {
            System.out.println(-1);
        }
    }
}
