package day10.p11659;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] numList;
    static int[] sumArray;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day10/p11659/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numList = new int[N + 1];
        sumArray = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
            sumArray[i] = sumArray[i - 1] + numList[i];
        }

        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            System.out.println(sumArray[to] - sumArray[from - 1]);
        }
    }
}
