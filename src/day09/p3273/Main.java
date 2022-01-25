package day09.p3273;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] numList;
    static long X;
    static int total = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day09/p3273/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        numList = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        X = Long.parseLong(st.nextToken());

        Arrays.sort(numList);

        int leftPointer = 0;
        int rightPointer = N - 1;

        while (leftPointer <= rightPointer) {

            //예외 처리
            if (leftPointer == rightPointer) {
                leftPointer++;
                continue;
            }

            long currentSum = numList[leftPointer] + numList[rightPointer];
            if (currentSum == X) {
                total++;
            }

            if (currentSum > X) {
                rightPointer--;
            } else {
                leftPointer++;
            }
        }

        System.out.println(total);
    }
}
