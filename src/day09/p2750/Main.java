package day09.p2750;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] numList;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day09/p2750/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        numList = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            numList[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numList);

        for (int i : numList) {
            System.out.println(i);
        }

    }
}
