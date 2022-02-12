package day25.p11266;

/**
 * 참고: https://steady-coding.tistory.com/250
 */

import java.util.*;
import java.io.*;

public class Main {

    static int V, E;

    static int[] discovery;
    static boolean[] isCutNode;
    static List<Integer>[] graph;
    static int order = 1;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day25/p11266/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        discovery = new int[V + 1];
        isCutNode = new boolean[V + 1];
        graph = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        for (int i = 1; i <= V; i++) {
            if (discovery[i] == 0) {
                dfs(i, 1);
            }
        }

        int answer = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= V; i++) {
            if (isCutNode[i]) {
                answer++;
                sb.append(i + " ");
            }
        }

        System.out.println(answer);
        System.out.println(sb.toString());

    }

    public static int dfs(int now, int isRoot) {

        discovery[now] = order++;
        int ret = discovery[now];
        int child = 0;

        for (int next : graph[now]) {
            if (discovery[next] == 0) {
                // 자식들 체크
                child++;

                int low = dfs(next, 0);

                if (isRoot != 1 && low >= discovery[now]) {
                    isCutNode[now] = true;
                }
                ret = Math.min(ret, low);
            } else {
                ret = Math.min(ret, discovery[next]);
            }
        }

        if (isRoot == 1 && child >= 2) {
            isCutNode[now] = true;
        }

        return ret;
    }

}
