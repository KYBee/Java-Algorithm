package day05.p13251;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int M;
    static int K;
    static int[] stone;
    static int totalStone = 0;
    static double percentage = 0;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day05/p13251/input4.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        stone = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            stone[i] = Integer.parseInt(st.nextToken());
            totalStone += stone[i];
        }

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            int total = totalStone;
            double percent = 1;

            for (int j = 0; j < K; j++) {
                percent *= (double) stone[i] / (double) total;
                total--;
                stone[i]--;
            }
            percentage += percent;
        }

        System.out.println(percentage);

    }

}
