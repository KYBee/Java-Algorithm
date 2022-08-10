package example.code.indexed.tree;

//백준 2042번 기준 참고
/**
 * 인덱스 트리 : 자료의 변경과 조회가 빈번하게 일어나는 경우에 효율적인 자료구조
 * 두 리프 노드의 정보를 부모 노드에 기록해 두는 것. 이게 루트 노드까지 기록됨
 *
 * 구현해야 하는 ADT
 *
 * N -> Node 수
 * S -> 완전 이진 트리로 만들기 위해 필요한 최소 노드의 수. 꼭 N과 같을 필요는 없지만 N보다는 크거나 같음
 *
 * init() -> 초기화 -> 초기화는 Bottom Up으로만 구현 -> 하위 노드를 다 채운 뒤 상위에 이를 반영하기 위해
 *
 * query(int left, int right, int node, int leftQuery, int rightQuery) -> 탑다운과 바텀업 둘 다 가능
 *      -> left 현재 구간의 왼쪽
 *      -> right 현재 구간의 오른쪽
 *      -> node 현재 노드의 번호
 *      -> leftQuery 조회하고자 하는 범위의 왼쪽
 *      -> rightQuery 조회하고자 하는 범위의 오른쪽
 *
 * update(int left, int right, int node, int target, long diff) -> 탑다운과 바텀업 둘 다 가능
 *      -> left 현재 구간의 왼쪽
 *      -> right 현재 구간의 오른쪽
 *      -> node 현재 노드의 번호
 *      -> leftQuery 조회하고자 하는 범위의 왼쪽
 *      -> rightQuery 조회하고자 하는 범위의 오른쪽
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // N: 실제 노드의 수, M: 변경을 일으키는 명령의 수, K: 구간의 합을 구하는 명령
    static int N, M, K;
    // 트리 구축을 위한 배열 자료구조
    static long[] indexedTree;
    static long[] numList;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        numList = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numList[i] = Long.parseLong(st.nextToken());
        }

        //S를 구함 -> S는 인덱스 트리를 완전 이진 트리로 만들기 위한 리프 노드의 최소 갯수
        //현재 N이 5이므로 S는 8이 되어야 함
        S = 1;
        while (N > S) {
            S *= 2;
        }

        //인덱스 트리를 S의 개수 * 2 만큼 초기화 -> S(모든 리프 노드의 수) + S(그 위의 노드 수)
        indexedTree = new long[2 * S];

        //인덱스 트리를 Bottom Up 방식으로 초기화
        initBU();

        for (int i = 0; i < K + M; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());

            if(op == 1) {
                // Update
                int target = Integer.parseInt(st.nextToken());
                long to = Long.parseLong(st.nextToken());
                long diff = to - indexedTree[S + target - 1];
                updateTD(1, S, 1, target, diff);
            } else {
                int leftQuery = Integer.parseInt(st.nextToken());
                int rightQuery = Integer.parseInt(st.nextToken());
                System.out.println(queryTD(1, S, 1, leftQuery, rightQuery));
            }
        }
    }


    public static void initBU() {
        //밑의 노드를 초기화
        for (int i = 0; i < N; i++) {
            indexedTree[S + i] = numList[i];
        }

        //타고 올라가면서 부모 노드 초기화 -> 범위 주의
        for (int i = S - 1; i > 0; i--) {
            indexedTree[i] = indexedTree[i * 2] + indexedTree[i * 2 + 1];
        }
    }

    public static long queryTD(int left, int right, int node, int leftQuery, int rightQuery) {

        //3가지 경우의 수 존재
        //1. 쿼리 경계에 포함되지 않는 경우
        //2. 쿼리 경계에 맞는 경우
        //3. 쿼리 경계에 걸치는 경우
        if (right < leftQuery || rightQuery < left) {
            return 0;
        } else if (leftQuery <= left && right <= rightQuery) {
            return indexedTree[node];
        } else {
            // 경계에 걸쳐서 경계를 미드를 기준으로 둘로 나눔 -> 왼쪽은 node * 2, 오른쪽은 node * 2 + 1
            int mid = (left + right) / 2;
            long resultLeft = queryTD(left, mid, node * 2, leftQuery, rightQuery);
            long resultRight = queryTD(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return resultLeft + resultRight;
        }
    }

    public static void updateTD(int left, int right, int node, int target, long diff) {

        //연관이 없는 경우
        if (target < left || right < target) {
            return;
        } else {
            indexedTree[node] += diff;
            if(left != right) {
                int mid = (left + right) / 2;
                updateTD(left, mid, node * 2, target, diff);
                updateTD(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }
}
