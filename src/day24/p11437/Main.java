package day24.p11437;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K = 0;

    static int[] depth;
    static int[][] parent; // parent[K][V] : 정점 V의 2^k 번째 조상정점 번호

    static ArrayList<Integer>[] tree;


    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day24/p11437/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        
        //2 ^ K > N 인 K 찾기
        for (int i = 1; i <= N; i *= 2) {
            K++;
        }

        // LCA 초기화
        depth = new int[N + 1];
        parent = new int[K][N + 1];

        // Tree
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // Tree 연결
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            tree[v1].add(v2);
            tree[v2].add(v1);
        }

        // Depth 테이블 초기화
        dfs(1, 1);

        // Parent 테이블 채우기 DP
        fillParent();

        //LCA 진행
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            //공통 조상 출력
            sb.append(LCA(v1, v2) + "\n");
        }

        System.out.println(sb);
    }

    public static int LCA(int a, int b) {
        //1. depth[a] >= depth[b] 로 조정하기 -> 항상 a 가 더 깊은 뎁스를 가지도록
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        
        //2. 더 깊은 a를 점프해서 depth를 맞춰주기
        for (int i = K - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b])
                a = parent[i][a];
        }

        //3. Depth를 맞췄는데 같다면 종료
        if (a == b)
            return a;

        //4. a - b 는 같은 depth이므로 2^k 승 점프해서 공통부모 바로 아래까지 올리기
        for (int i = K - 1; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }

    //Parent 테이블 채우기
    public static void fillParent() {
        //0 번째 부모는 다 기록되어 있으니까
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++) {
                // 모든 노드에 대해서

                //K 번째 조상 정점 번호를 찾는다.
                //TODO: 그냥 일단 외워
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }            
        }
    }
    
    

    //Depth 확인을 위함
    public static void dfs(int id, int cnt) {

        depth[id] = cnt;

        for (int next : tree[id]) {

            if(depth[next] == 0) {
                dfs(next, cnt + 1);

                //바로 위 부모를 기록
                parent[0][next] = id;
            }
        }
    }
}
