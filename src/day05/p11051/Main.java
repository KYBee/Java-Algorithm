package day05.p11051;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int pascal[][] = new int[1001][1001];

    public static int getCombination(int n, int r) {

        // 만약  r == 0이거나 n == r 이면 1
        if (r == 0 || n == r) {
            return 1;
        }
        // 만약 pascal 배열에 이미 값이 있으면 그 값을 사용
        else if (pascal[n][r] != 0){
            return pascal[n][r];
        }
        // 값이 없다면 등록 후 값을 리턴
        else {
            pascal[n][r] = getCombination(n-1, r-1) + getCombination(n-1, r);
            return pascal[n][r] % 10007;
        }
    }

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day05/p11051/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int result = getCombination(N, K);
        System.out.println(result);
    }
}
