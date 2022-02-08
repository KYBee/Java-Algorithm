package day19.p2470;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day19/p2470/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numList = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numList);

        int front = 0, end = N - 1;
        int current;
        int max = 2000000000;
        int p1 = 0;
        int p2 = 0;

        do {
            current = numList[front] + numList[end];

            if (Math.abs(current) < max) {
                max = Math.abs(current);
                p1 = front;
                p2 = end;
            }

            if (current == 0) {
                break;
            } else if (current < 0) {
                front++;
            } else {
                end--;
            }

        } while (front < end);

        System.out.println(numList[p1] + " " + numList[p2]);
    }
}
