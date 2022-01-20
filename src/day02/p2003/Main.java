package day02.p2003;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //TODO
        /**
         * 1. 배열 선언
         * 2. 두개의 포인터를 선언
         * 3. 두개의 포인터를 더해가면서 조건 비교
         *     - 만약 합이 M보다 작으면 -> H++
         *     - 만약 합이 M과 같으면 -> 기록 후 L++
         *     - 만약 합이 M보다 크면 -> L++
         * 4. L과 H를 적절한 시점에 종료 시켜줌
         * */


        System.setIn(new FileInputStream("src/day02/p2003/input2.txt"));
        Scanner sc = new Scanner(System.in);

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        //int N =Integer.parseInt(st.nextToken());

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] numberList = new int[N];
        int count = 0;

        // 1. 숫자 배열 선언
        for (int i = 0; i < N; i++) {
            numberList[i] = sc.nextInt();
        }

        // 2. 2개의 포인터 선언
        int L = 0;
        int H = 0;
        int sum = 0;

        while (H < N) {
            // 3. 합을 구함
            sum = numberList[L];
            for(int i = L + 1; i <= H; i++) {
                sum += numberList[i];
            }

            // 4. 합의 조건을 비교
            if (sum < M) {
                H++;
            } else if (sum > M) {
                L++;
                H=L;
            } else {
                //결과를 저장함

                //debugging
                //for(int i = L; i <= H; i++) {
                //    System.out.print(numberList[i] + " ");
                //}
                //System.out.println();
                count++;
                L++;
                H = L;
            }

        }

        //Debugging
        //System.out.println(N);
        //System.out.println(M);
        //System.out.println(Arrays.toString(numberList));

        System.out.println(count);
    }
}
