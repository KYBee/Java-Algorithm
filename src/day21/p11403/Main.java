package day21.p11403;

import java.util.*;
import java.io.*;

public class Main {

    static int V;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day21/p11403/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        map = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= V; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * 1. 일단 모두 한바퀴씩 돌아
         * 2. 돌면서 i j 가 같은것은 제외하고
         * 3. 다른 것들 중에서 경로가 있을 때 => 두 경로가 0이 아닐 때
         * 4. 그 둘과 현재 값을 비교해서 아직 값이 안 채워졌거나 값이 작아서 수정이 필요하면 바꿔
         * */
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                   if (map[i][k] == 1 && map[k][j] == 1){
                       map[i][j] = 1;
                   }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
