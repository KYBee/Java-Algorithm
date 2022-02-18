package example.code.LCA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K = 1;
    static int[] depth;
    static int[][] parent;

    static List<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day31/p11437/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        // K를 구한다.
        for (int i = 1; i <= N; i = i * 2) {
            K++;
        }

        // depth, parent 초기화
        depth = new int[N + 1];
        parent = new int[K][N + 1];

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }


        // dfs 로 모든 뎁스와 부모를 저장하고
        dfs(1, 1);

        // fillParent로 dp 기록함
        fillParent();


        //LCA 를 함
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            System.out.println(LCA(n1, n2));
        }
    }

    public static void dfs(int id, int count) {
        depth[id] = count;

        for (int child : graph[id]) {
            if (depth[child] == 0) {
                dfs(child, count+ 1);
                //부모를 기록
                parent[0][child] = id;
            }
        }
    }

    public static void fillParent() {
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
    }

    public static int LCA(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        
        //1. 일단 점프
        for (int i = K - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parent[i][a];
            }
        }
        
        //2. 같으면 돌리고
        if (a == b){
             return a;
        }
        
        //3. 점프해도 다르면 하나씩 올리고
        for (int i = K - 1; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }
}
