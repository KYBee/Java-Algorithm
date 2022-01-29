package day12.p1517;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long[] numList;
    static long[] indexedTree;
    static long[] indexList;
    static long[] finalList;
    static long totalCount = 0;
    static int S = 1;

    static Set<Long> set = new HashSet<>();
    static Map<Long, Integer> dict = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day12/p1517/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        numList = new long[N];
        indexList = new long[N];
        finalList = new long[N];

        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
            indexList[i] = numList[i];
        }

        int idx = 1;
        Arrays.sort(indexList);

        finalList[0] = indexList[0];
        for (int i = 1; i < N; i++) {
            if (finalList[idx - 1] != indexList[i]) {
                finalList[idx++] = indexList[i];
            }
        }

        // index
        idx = 1;
        for (long num : finalList) {
            dict.put(num, idx);
            idx++;
        }

        while (S <= idx) {
            S *= 2;
        }

        indexedTree = new long[2 * S];


        for (int i = N - 1; i >= 0; i--) {
            long current = numList[i];
            int currentIndex = dict.get(current);
            //1. update
            update(1, S, 1, currentIndex);

            if (currentIndex > 1) {
                totalCount += query(1, S, 1, 1, currentIndex - 1);
            }

            //System.out.println(Arrays.toString(indexedTree));
        }

        System.out.println(totalCount);
    }

    public static void update(int left, int right, int node, int target) {

        // 타겟의 범위 설정
        if (target < left || right < target) {
            return;
        } else {
            indexedTree[node] += 1;

            //leaf 노드라면 끝
            if (left == right) {
                return;
            } else {
                int mid = (left + right) / 2;

                update(left, mid, node * 2, target);
                update(mid + 1, right, node * 2 + 1, target);
            }
        }
    }

    public static long query(int left, int right, int node, int leftQuery, int rightQuery) {

        if (rightQuery < left || right < leftQuery) {
            return 0;
        } else if (leftQuery <= left && right <= rightQuery) {
            return indexedTree[node];
        } else {
            int mid = (left + right) / 2;
            long leftValue = query(left, mid, node * 2, leftQuery, rightQuery);
            long rightValue = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return leftValue + rightValue;
        }
    }
}
