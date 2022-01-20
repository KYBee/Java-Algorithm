package day04.p2042;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static long[] indexedTree;
    static long[] numList;
    static int S;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day04/p2042/input.txt"));
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

        //S를 구함
        S = 1;
        while (N > S) {
            S *= 2;
        }

        indexedTree = new long[2 * S];

        //init complete
        initBU();


        for (int i = 0; i < K + M; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());

            if(op == 1) {
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

        for (int i = 0; i < N; i++) {
            indexedTree[S + i] = numList[i];
        }

        for (int i = S - 1; i > 0; i--) {
            indexedTree[i] = indexedTree[i * 2] + indexedTree[i * 2 + 1];
        }
    }

    public static long queryTD(int left, int right, int node, int leftQuery, int rightQuery) {

        //3가지 경우의 수 존재
        //1. 쿼리 경계에 포함되지 않는 경우
        //2. 쿼리 경계에 맞는 경우
        //3. 쿼리 경계에 걸치는 경우
        if (right < leftQuery || left > rightQuery) {
            return 0;
        } else if (left >= leftQuery && rightQuery >= right) {
            return indexedTree[node];
        } else {
            int mid = (left + right) / 2;
            long resultLeft = queryTD(left, mid, node * 2, leftQuery, rightQuery);
            long resultRight = queryTD(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return resultLeft + resultRight;
        }
    }

    public static void updateTD(int left, int right, int node, int target, long diff) {
//
//        int mid = left + right / 2;
//
//        indexedTree[node] += diff;
//
//        // 도착한게 leaf 노드면 조치를 취함
//        if (node == S + target - 1) {
//            return;
//        }
//
//        //리프 노드가 아니라면
//        //일단 target이 left, mid    mid + 1, right 중 어디에 속하는지 확인
//
//        if (target < mid) {
//            //left child에 diff를 적용하고 left 아래로 재귀를 보냄
//            updateTD(left, mid, node * 2, target, diff);
//        } else {
//            //right child에 diff를 적용하고 right 아래로 재귀를 보냄
//            updateTD(mid + 1, right, node * 2 + 1, target, diff);
//        }


        //연관이 없는 경우
        if (left > target || right < target) {
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
