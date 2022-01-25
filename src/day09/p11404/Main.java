package day09.p11404;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] result;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day09/p11404/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        result = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(result[from][to] == 0 || result[from][to] > weight) {
                result[from][to] = weight;
            }
        }

        getPath();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void getPath() {
        //가장 중간 노드
        for (int k = 1; k <=n; k++) {
            for (int i = 1; i <=n ; i++) {
                for (int j = 1; j <=n ; j++) {
                    if(i != j && result[i][k] != 0 && result[k][j] != 0) {
                        if(result[i][j] == 0 || result[i][j] > result[i][k] + result[k][j]) {
                            result[i][j] = result[i][k] + result[k][j];
                        }
                    }
                }
            }
        }
    }
}
