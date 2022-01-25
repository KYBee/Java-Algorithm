package day08.p11657;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main<i, fo> {

    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static Edge[] graphs;
    static long[] map;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day08/p11657/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int A, B, C;

        map = new long[N + 1];
        graphs = new Edge[M + 1];
        long[] newMap = new long[N + 1];


        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            graphs[i] = new Edge(A, B, C);
        }

        //V -> M이니까 for 문은 V 번 돌면 되고 이차원 배열은 V, N개 선언하면 됨
        for (int i = 2; i <= N; i++) {
            map[i] = INF;
            newMap[i] = INF;
        }

        // 가중치 넣기
        for (int i = 1; i <= M - 1; i++) {
            for (int j = 1; j < graphs.length; j++) {
                Edge currentEdge = graphs[j];
                int from = currentEdge.A;
                int to = currentEdge.B;
                int weight = currentEdge.C;

                //INF 값이 정수라서 음의 가중치와 만나면 min 값이 의도치않게 계산될 수 있기 때문에
                //INF 도 잘 계산해주어야 함
                if (map[from] != INF) {
                    map[to] = Math.min(map[to], map[from] + weight);
                }
            }
        }


        // 한 번 더 돌려서 negative cycle이 있는지 확인
        for (int i = 1; i < graphs.length; i++) {
            Edge currentEdge = graphs[i];
            int from = currentEdge.A;
            int to = currentEdge.B;
            long weight = currentEdge.C;

            if (map[from] != INF) {
                newMap[to] = Math.min(map[to], map[from] + weight);
            }

            if (newMap[to] != map[to]) {
                System.out.println(-1);
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            if (map[i] == INF) {
                sb.append("-1\n");
            } else {
                sb.append(map[i]).append("\n");
            }
        }

        System.out.println(sb.toString());
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