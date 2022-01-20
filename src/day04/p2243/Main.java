package day04.p2243;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;

    static final int TREE_SIZE = 1000000;
    static int S;
    static int[] indexedTree;;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day04/p2243/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        int A, B, C;

        S = 1;
        while (S < TREE_SIZE) {
            S *= 2;
        }
        indexedTree = new int[S * 2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            if (A == 1) {
                // 사탕을 뺴는 경우
                int index = queryTD(1, S, 1, B);
                System.out.println(index);
                updateTD(1, S, 1, index, -1);

            } else {
                // 사탕을 넣는 경우
                C = Integer.parseInt(st.nextToken());
                updateTD(1, S, 1, B, C);
            }
        }
    }


    static int queryTD(int left, int right, int node, int target) {

        //1. value를 비교해서 왼쪽과 오른쪽으로 넘겨준다.
        //트리에 등록된 수가 타겟보다 작으
        if (left == right) {
            return left;
        }

        int mid = (left + right) / 2;
        if (indexedTree[node * 2] >= target) {
            //왼쪽으로
            return queryTD(left, mid, node * 2, target);

        } else {
            //오른쪽으로

            return queryTD(mid + 1, right, node * 2 + 1, target - indexedTree[node * 2]);
        }
    }

    static void updateTD(int left, int right, int node, int target, int diff) {

        //1. 구간 안에 없으면 넘어간다.
        //2. 구간 안에 있다면
        //   3. 만약 리프 노드이면 끝
        //   4. 리프 노드가 아니면 자식으로 넘겨줘야 함

        if (left > target || right < target) {
            return;
        } else {

            indexedTree[node] += diff;

            if (left != right) {

                int mid = (left + right) / 2;
                updateTD(left, mid, node * 2, target, diff);
                updateTD(mid + 1, right, node * 2 + 1, target, diff);
            }
        }

    }
}
