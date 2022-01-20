package day02.p1806;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int S;
    static int[] numList;
    static int count = 0;
    static boolean answer = false;

    public static void main(String[] args) throws IOException {

        //TODO
        /**
         * 1. 배열을 받은 다음
         * 2. H L 를 선언하고
         * 3. 두 수의 합을 구함
         *    - 만약 두 수의 합이 S 보다 크다면
         *       - Count와 현재 두 수의 차이를 비교함 더 작은걸 count에 대입
         *    - 만약 두 수의 합이 S 보다 작으면 H를 ++ 해줌
         * */

        System.setIn(new FileInputStream("src/day02/p1806/input1.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        numList = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        int low = 0, high = 0, sum = numList[0];

        while (true) {

            if (sum < S) {
                if (high < N - 1) {
                    sum += numList[++high];
                } else {
                    break;
                }
            } else {
                if (!answer) {
                    answer = true;
                    count = high - low + 1;
                } else {
                    count = count > high - low + 1 ? high - low + 1: count;
                }

                sum -= numList[low++];
            }


        }

        //debugging
        //for (int i = 0; i < N; i++) {
        //    System.out.println(numList[i]);
        //}

        System.out.println(count);

    }
}
