package example.code.topology.sort;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] level;
    static List<Integer>[] map;
    static List<Integer> path = new LinkedList<>();
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/example/code/topology/sort/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        level = new int[N + 1];
        map = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map[from].add(to);
            level[to]++;
        }

        for (int i = 1; i <= N; i++) {
            if (level[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int current = q.poll();

            for (int n: map[current]) {
                level[n]--;
                if (level[n] == 0) {
                    q.add(n);
                }
            }

            path.add(current);
        }

        System.out.println(path.toString());
    }
}
