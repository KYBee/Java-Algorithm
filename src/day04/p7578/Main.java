package day04.p7578;

import java.io.*;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int S = 1;
    static long[] order;
    static Map<Long, Integer> dest = new HashMap<>();
    static long[] indexedTree;
    static long totalCount = 0;


    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day04/p7578/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        order = new long[N];
        while (S < N) {
            S *= 2;
        }
        indexedTree = new long[S * 2];

        for (int i = 0; i < N; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dest.put(Long.parseLong(st.nextToken()), i + 1);
        }

        //하나씩 넘어가면서 query를 먼저 날리고 그 다음 업데이트

        for (long num: order) {

            int leftQuery = dest.get(num);

            long count = queryTD(1, S, 1, leftQuery + 1, S);
            totalCount += count;
            updateTD(1, S, 1, leftQuery);

        }

        System.out.println(totalCount);

    }

    public static long queryTD(int left, int right, int node, int leftQuery, int rightQuery) {
        if (leftQuery > right || rightQuery < left) {
            //1. 범위가 벗어난 경우
            return 0;
        } else if (leftQuery <= left && rightQuery >= right) {
            //2. 범위가 안에 있는 경우
            return indexedTree[node];
        } else {
            //3. 범위가 걸친 경우
            int mid = (left + right) / 2;
            return queryTD(left, mid, node * 2, leftQuery, rightQuery) + queryTD(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
        }
    }

    public static void updateTD(int left, int right, int node, int target) {
        //1. 연관이 없는 경우
        if (left > target || right < target) {
            return;
        } else {
            indexedTree[node] += 1;
            //현재 노드가 리프가 아니라면
            if (left != right) {
                int mid = (left + right) / 2;
                updateTD(left, mid, node * 2, target);
                updateTD(mid + 1, right, node * 2 + 1, target);
            }
        }
    }

}
