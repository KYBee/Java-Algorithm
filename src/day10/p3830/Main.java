package day10.p3830;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int[] parent;
    static long[] weightDiff;
    static long Answer;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day10/p3830/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            int N, M;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            parent = new int[N + 1];
            weightDiff = new long[N + 1];

            //Init
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            int a, b, diff;
            String op;
            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                op = st.nextToken();
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                if (op.equals("?")) {
                    // 교수가 질의를 하는 것
                    if (find(a) == find(b)) {
                        Answer = weightDiff[a] - weightDiff[b];
                        sb.append(Answer).append("\n");
                    } else {
                        sb.append("UNKNOWN\n");
                    }
                } else {
                    // 상근이가 등록을 하는 것
                    diff = Integer.parseInt(st.nextToken());
                    union(a, b, diff);
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static void union(int a, int b, int diff) {

        int aParent = find(a);
        int bParent = find(b);

        //이미 연결되어 있으면 더이상 수정하지 않음
        if (aParent == bParent) {
            return;
        }

        weightDiff[aParent] = weightDiff[b] - weightDiff[a] + diff;
        parent[aParent] = bParent;
    }

    public static int find(int a) {

        if (a == parent[a]) {
            return a;
        } else {
            int tempIndex = parent[a];
            parent[a] = find(parent[a]);
            weightDiff[a] += weightDiff[tempIndex];
            return parent[a];
        }
    }
}