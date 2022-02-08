package day19.p1644;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static boolean[] isNotPrime;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day19/p1644/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        isNotPrime = new boolean[N + 1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;

        //소수는 false
        getPrime();
        int front = 0, end = 0;
        int sum = 0;

        for (int i = 2; i <= N; i++) {
            sum = 0;

            if (!isNotPrime[i]) {
                sum += i;
                if (sum == N) {
                    answer++;
                } else {
                    for (int j = i + 1; j <= N; j++) {
                        if (!isNotPrime[j]) {
                            sum += j;
                            if (sum == N) {
                                answer++;
                                break;
                            } else if (sum > N) {
                                break;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(answer);

    }

    public static void getPrime() {
        for (int i = 2; i <= N; i++) {
            if (isNotPrime[i] == false) {
                for (int j = i * 2; j <= N; j = j + i) {
                    isNotPrime[j] = true;
                }
            }
        }
    }
}
