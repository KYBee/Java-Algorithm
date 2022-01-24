package day08.p2517;

import java.io.*;
import java.util.*;

public class Main {

    static int N, S;
    static int[] players;
    static int[] result;
    static Map<Integer, Integer> index = new HashMap<>();
    static int[] indexedTree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day08/p2517/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        players = new int[N];
        result = new int[N];
        // 선수들이 달리는 순서들을 기록함
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            players[i] = Integer.parseInt(st.nextToken());
            index.put(players[i], i + 1);
        }

        // 인덱스 트리 구성
        S = 1;
        while (S <= N) {
            S *= 2;
        }
        indexedTree = new int[2 * S];


        // query, update 구현 후 player리스트를 sorting 함
        Arrays.sort(players);

        // indexed tree에 업데이트 해주고 앞에 얼마나 많은 사람이 있는지를 출력함
        for (int i = N - 1; i >= 0 ; i--) {
            int idx = index.get(players[i]);
            int diff = query(1, S, 1, 1, idx - 1);
            update(1, S, 1, idx);
            result[idx - 1] = diff + 1;
        }

        for (int i = 0; i < N; i++) {
            sb.append(result[i]);
            sb.append("\n");
        }

        System.out.print(sb.toString());

    }

    public static void update(int left, int right, int node, int target) {

        // target이 범위 안에 없으면 out
        if (target < left || right < target) {
            return;
        }

        // 일단 ++를 해줌
        indexedTree[node]++;
        // 만약 left 가 right 와 같으면 이건 leaf 노드니까 리턴
        if (left == right) {
            return;
        } else {
            // 만약 leaf노드가 아니면 left right 로 분기
            int mid = (left + right) / 2;
            update(left, mid, node * 2, target);
            update(mid + 1, right, node * 2 + 1, target);
        }
    }

    public static int query(int left, int right, int node, int leftQuery, int rightQuery) {

        // 범위 밖에 있는 경우
        if (leftQuery > right || rightQuery < left) {
            return 0;
        } else if (leftQuery <= left && right <= rightQuery) {
            // 범위 안에 있는 경우
            return indexedTree[node];
        } else {
            // 범위에 걸친 경우
            int mid = (left + right) / 2;
            int leftValue = query(left, mid, node * 2, leftQuery, rightQuery);
            int rightValue = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return leftValue + rightValue;
        }
    }

}
