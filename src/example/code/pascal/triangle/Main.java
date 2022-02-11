package example.code.pascal.triangle;

import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static int[][] pascal = new int[20][20];

    public static int getCombination(int n, int r) {

        // 만약  r == 0이거나 n == r 이면 1
        if (r == 0 || n == r) {
            return pascal[n][r] = 1;
        }
        // 만약 pascal 배열에 이미 값이 있으면 그 값을 사용
        else if (pascal[n][r] != 0){
            return pascal[n][r];
        }
        // 값이 없다면 등록 후 값을 리턴
        else {
            pascal[n][r] = getCombination(n-1, r-1) + getCombination(n-1, r);
            return pascal[n][r];
        }
    }

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/example/code/pascal/triangle/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        System.out.println(getCombination(N, K));

        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(pascal[i]));
        }
    }
}
