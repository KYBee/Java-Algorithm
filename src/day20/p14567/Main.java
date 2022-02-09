package day20.p14567;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] subject;
    static int[] degree;

    static ArrayList<Integer> [] map;
    static Queue<Integer> q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day20/p14567/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        subject = new int[N + 1];
        degree = new int[N + 1];
        map = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map[from].add(to);
            degree[to] += 1;
        }

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                q.add(i);
                subject[i] = 1;
            }
        }


        while(!q.isEmpty()) {
            int current = q.poll();

           for (int sub : map[current]) {
               degree[sub]--;

               if (degree[sub] == 0 && subject[sub] == 0) {
                   subject[sub] = subject[current] + 1;
                   q.add(sub);
               }
           }
        }


        for (int i = 1; i <= N; i++) {
            sb.append(subject[i]).append(" ");
        }

        System.out.println(sb.toString());
    }
}
