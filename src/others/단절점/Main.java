package others.단절점;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int order = 1;
    static int[] discover;
    static boolean[] isCutVertex;
    static List<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/others/단절점/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.valueOf(st.nextToken()); // 정점의 개수
        M = Integer.valueOf(st.nextToken()); // 간선의 개수

        graph = new ArrayList[N + 1];
        
        // 탐색순서
        discover = new int[N + 1];
        isCutVertex = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        for (int i = 1; i <= N; i++) {
            if (discover[i] == 0) {
                dfs(i, true);
            }
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (isCutVertex[i] == true) {
                count++;
                sb.append(i + " ");
            }
        }

        System.out.println(count);
        System.out.println(sb);
    }

    public static int dfs(int vertex, boolean isRoot) {

        //방문 순서를 먼저 저장함
        discover[vertex] = order++;
        //ret 으로 방문 순서 저장 -> 비교를 위해서
        int ret = discover[vertex];
        //자식의 수 세기
        int child = 0;

        for (int now : graph[vertex]) {
            if (discover[now] == 0) {
                child++;
                int low = dfs(now, false);

                //만약 루트가 아닌데 -> 자식이 자기보다 더 이전 노드 방문을 못한다. 그럼 단절점
                if (!isRoot && low >= discover[vertex]) {
                    isCutVertex[vertex] = true;
                }

                ret = Math.min(low, ret);
            } else {
                ret = Math.min(ret, discover[now]);
            }
        }

        //루트 노드이면 체크
        if (isRoot && child >= 2) {
            isCutVertex[vertex] = true;
        }

        return ret;
    }
}
