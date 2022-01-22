package day06.p1065;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day06/p1065/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            result += isHansoo(i);
        }

        System.out.println(result);


    }

    public static int isHansoo(int n) {
        if (n < 100) {
            return 1;
        } else {
            if (n == 1000) return 0;

            String numString = String.valueOf(n);
            int diff1 = numString.charAt(2) - numString.charAt(1);
            int diff2 = numString.charAt(1) - numString.charAt(0);

            if (diff1 == diff2) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
