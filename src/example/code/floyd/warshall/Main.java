package example.code.floyd.warshall;

import java.io.*;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int V, E;
    static long[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/example/code/floyd/warshall/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        map = new long[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            Arrays.fill(map[i], INF);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[from][to] = weight;
        }

        for (int i = 1; i <= V; i++) {
            System.out.println(Arrays.toString(map[i]));
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 0; i <= V; i++) {
                for (int j = 0; j <= V; j++) {
                    if (i == j) {
                        map[i][j] = 0;
                        continue;
                    }
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
