package day03.p2805;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long M;
    static long[] trees;
    static long H = 0;

    public static void main(String[] args) throws IOException {

        /**
         * 1. 이분탐색 : 정확히 어떤 특정 값을 찾는 것
         * 2. 파라매트릭 서치 : 정확히 어떤 값을 찾는 걔념은 아니고 특정 조건을 만족하는 값을 찾는 것
         * */



        System.setIn(new FileInputStream("src/day03/p2805/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        trees = new long[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(trees);

        long start = 0, end = trees[trees.length - 1];
        long mid = (start + end) / 2;
        long treeTotal = 0;

        while (start <= end) {

            treeTotal = 0;
            mid = (start + end) / 2;

            for (Long tree : trees) {
                if (tree > mid) {
                    treeTotal += tree - mid;
                }
            }

            if (treeTotal == M) {
                H = mid;
                break;
            } else if (treeTotal < M) {
                //어쩌피 현재 mid는 유효하지 않으므로 -1을 함
                end = mid - 1;
            } else {
                start = mid + 1;
                //현재 mid는 유효할 수 있지만 어쩌피 mid를 밑의 코드에서 비교해서 H에 넣으므로 +1을 함
                H = mid > H ? mid : H;
            }
        }


        System.out.println(H);

    }

}

