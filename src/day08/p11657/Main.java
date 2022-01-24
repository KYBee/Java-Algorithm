package day08.p11657;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 10001;
    static int N, M;
    static List<Edge> graphs = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day08/p11657/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int A, B, C;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            graphs.add(new Edge(A, B, C));
        }

        //V -> M이니까 for 문은 V 번 돌면 되고 이차원 배열은 V, N개 선언하면 됨
        int[][] map = new int[M + 1][N];
        for (int i = 1; i < N; i++) {
            map[0][i] = INF;
        }

        for (int i = 1; i <= M - 1; i++) {

            for (Edge e : graphs) {
                // 간선이 나오면
                // 이 위에 것과 비교해서 넣는다.


            }
        }


    }

}

class Edge {

    int A;
    int B;
    int C;

    public Edge(int a, int b, int c) {
        A = a;
        B = b;
        C = c;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "A=" + A +
                ", B=" + B +
                ", C=" + C +
                '}';
    }
}