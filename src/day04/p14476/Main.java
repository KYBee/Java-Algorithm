package day04.p14476;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] numList;
    static int[] LR;
    static int[] RL;
    static int K;
    static int finalGCD = -1;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day04/p14476/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        numList = new int[N];

        Arrays.sort(numList);

        LR = new int[N];
        RL = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        LR[0] = numList[0];
        RL[N - 1] = numList[N - 1];

        for (int i = 1; i < N; i++) {

            //LR 은 1부터 N - 1까지
            LR[i] = gcd(numList[i], LR[i - 1]);

            //RL 은 N - 1 부터 0까지
            RL[N - 1 - i] = gcd(RL[N - i], numList[N - i - 1]);
        }


        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < N; i ++) {
            int gcd = 0;
            if (i == 0) {
                gcd = RL[1];
            } else if (i == N - 1) {
                gcd = LR[N - 2];
            } else {
                gcd = gcd(LR[i - 1], RL[i + 1]);
            }

            if (numList[i] % gcd != 0 && gcd > max) {
                max = gcd;
                maxIndex = i;
            }
        }

        if (max == 0) {
            System.out.println(-1);
        } else {
            System.out.format("%d %d", max, numList[maxIndex]);
        }

    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

//    //recursion
//    public static int gcd(int a, int b) {
//        if (a % b == 0) {
//            return b;
//        } else {
//            return gcd(b, a % b);
//        }
//    }
}
