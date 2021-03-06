package day21.p1865;

import java.util.*;
import java.io.*;

public class Main {

    static int TC, V, E, W;
    static final long INF = Long.MAX_VALUE;
    static long[] result;
    static ArrayList<Edge>[] edge;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day21/p1865/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        TC = Integer.parseInt(br.readLine());

        for (int i = 0; i < TC; i++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            
            result = new long[V + 1];
            edge = new ArrayList[V + 1];
            for (int j = 1; j <= V; j++) {
                edge[j] = new ArrayList<>();
            }
            Arrays.fill(result, INF);

            int j = 0;
            for (; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                long weight = Long.parseLong(st.nextToken());

                edge[from].add(new Edge(from, to, weight));
                edge[to].add(new Edge(to, from ,weight));
            }

            for (; j < E + W; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                long weight = -1 * Long.parseLong(st.nextToken());

                edge[from].add(new Edge(from, to, weight));
            }

            if (bellmanFord()) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }

        }

        System.out.print(sb.toString());
    }

    public static boolean bellmanFord() {
        /**
         * 1. ?????? ???????????? ?????????
         * 2. ?????? ??? ?????????
         * 3. ????????? ????????? ????????? ?????? ????????? ?????? ????????? ??????
         * 4. ?????? v-1 ??????
         * */

        boolean update = false;
        for (int j = 1; j <= V; j++) {
            Arrays.fill(result, INF);
            result[j] = 0;
            
            for (int i = 1; i < V; i++) {
                update = false;
                for (int k = 1; k <= V; k++) {
                    for (Edge e : edge[k]) {
                        if (result[e.from] != INF) {
                            if (result[e.to] > result[e.from] + e.weight) {
                                result[e.to] = result[e.from] + e.weight;
                                update = true;
                            }
                        }
                    }
                }

                //????????? ??????????????? ???????????? ??????
                if(!update) {
                    break;
                }
            }

            if (update) {
                for (int i = 1; i <= V; i++) {
                    for (Edge e : edge[i]) {
                        if (result[e.from] != INF) {
                            if (result[e.to] > result[e.from] + e.weight) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}

class Edge {
    int from;
    int to;
    long weight;

    public Edge(int from, int to, long weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}