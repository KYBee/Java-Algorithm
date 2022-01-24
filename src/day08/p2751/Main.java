package day08.p2751;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] numList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day08/p2751/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        numList = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numList[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numList);

        for (int n : numList) {
            sb.append(n).append("\n");
        }

        System.out.println(sb.toString());
    }
}
