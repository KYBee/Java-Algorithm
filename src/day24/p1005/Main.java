package day24.p1005;

import java.util.*;
import java.io.*;

public class Main {

    static int TC;
    static int N, M, F;
    static int[] weight;
    static int[] time;
    static int[] path;
    static List<Integer>[] map;
    static Queue<Integer> q=  new LinkedList<>();

    public static void main(String[] args) throws IOException {
        
        System.setIn(new FileInputStream("src/day24/p1005/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        TC = Integer.parseInt(br.readLine());

        for (int i = 0; i < TC; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            weight = new int[N + 1];
            time = new int[N + 1];
            path = new int[N + 1];
            map = new ArrayList[N + 1];

            for (int j = 1; j <= N; j++) {
                map[j] = new ArrayList<>();
            }
            
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                weight[j] = Integer.parseInt(st.nextToken());
            }
            
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                map[from].add(to);
                path[to]++;
            }

            F = Integer.parseInt(br.readLine());

            for (int j = 1; j <= N; j++) {
                if (path[j] == 0) {
                    q.add(j);
                }
                time[j] = weight[j];
            }

            while (!q.isEmpty()) {
                int current = q.poll();

                for (int node : map[current]) {
                    path[node]--;
                    time[node] = Math.max(time[node], time[current] + weight[node]);

                    if (path[node] == 0) {
                        q.add(node);
                    }
                }
            }

            System.out.println(time[F]);
        }
    }
}
