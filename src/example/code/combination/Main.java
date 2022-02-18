package example.code.combination;

import java.util.*;
import java.io.*;

/**
 * Combination
 * 1. arr, visited, start, n, r
 *    arr : 배열
 *    visited : flag 값 최종 조합에 어떤 숫자들이 포함되는지를 체크함
 *    start : 시작점 . 만약 0번 인덱스를 시작으로 조합을 구성했으면 이제 더이상 0번으로 돌아올 필요 없음
 *    n : 전체 수
 *    r : 몇 개를 뽑을 것인지
 *
 * 2. 만약 뽑을 수가 0이라면 일단 지금까지 visited가 true인 것들을 뽑아서 조합을 완성시킴
 * 3. 0이 아니면
 *    1. 체크인
 *    2. start 를 하나 늘리고 (나 다음 원소부터) r 을 하나 줄임 (내가 이미 뽑혔으니)
 *    3. combination을 보냄
 *    4. 체크 아웃
 * */


public class Main {

    static int N;
    static int[] numList;
    static Set<Integer> possible = new HashSet<>();
    static Set<String> result = new HashSet<>();

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/example/code/combination/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        numList = new int[N];
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        combination(numList, new boolean[N], 0, N, 3);

        System.out.println(result);
        System.out.println(possible);
    }

    public static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            String str = "";
            int total = 0;

            for (int i = 0; i < n; i++) {
                if (visited[i] == true) {
                    str += arr[i];
                    total += arr[i];
                }
            }

            result.add(str);
            possible.add(total);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
}