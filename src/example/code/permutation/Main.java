package example.code.permutation;

import java.util.*;
import java.io.*;

/**
 * Permutation
 * 1. arr, output, visited, start, n, r
 *    arr : 순열 실행 배열
 *    output : 각 순열의 결과를 저장하는 배열
 *    visited : 순열 탐색 여부
 *    start : 몇 번째 순열 숫자를 뽑는 것인지
 *    n : 전체 arr의 크기
 *    r : 몇 개 뽑을 것인지
 * 2. r == start 이면 일단 다 뽑아둔 상태임. 알맞는 조치를 취하면 됨
 * 3. 순열이기 때문에 처음부터 다 돌면서
 *    1. 만약 visited -> false 이면
 *      갈 수 있기 때문에 간다
 *      visited 체크인
 *      output 에 현재 원소 넣고
 *      permutation(start + 1)
 *      output을 0으로 초기화
 *      visited 를 false로 초기화
 * */

public class Main {

    static int N;
    static int[] numList;
    static int[] output;

    static Set<String> result = new HashSet<>();

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/example/code/permutation/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numList = new int[N];
        output = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Arrays.toString(numList));

        permutation(numList, output, new boolean[N], 0, N, N);
        System.out.println(result);

    }

    public static void permutation(int[] arr, int[] output, boolean[] visited, int start, int n, int r) {
        if (start == r) {
            String str = "";
            for (int i = 0; i < r; i++) {
                str += output[i];
            }
            result.add(str);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[start] = arr[i];
                permutation(arr, output, visited, start + 1, n, r);
                output[start] = 0;
                visited[i] = false;
            }
        }
    }
}


