package day19.p1976;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day19/p1976/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = i;
        }

        StringTokenizer st;
        int from, to, connect;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            from = i;

            for (int j = 1; j <= N; j++) {
                to = j;
                connect = Integer.parseInt(st.nextToken());

                if (i == j) {
                    continue;
                }

                if (connect == 1) {
                    union(from, to);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        for (int i = 1; i < M; i++) {
            to = Integer.parseInt(st.nextToken());
            if (find(from) != find(to)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    public static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent > bParent) {
            map[bParent] = aParent;
        } else {
            map[aParent] = bParent;
        }
    }

    public static int find(int a) {
        if (a == map[a]) {
            return a;
        } else {
            return map[a] = find(map[a]);
        }
    }
}
