package day06.p2960;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day06/p2960/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int current;
        int deleteCount = 0;
        boolean isNotPrime[] = new boolean[N + 1];

        for (int i = 2; i < N + 1; i++) {

            if (isNotPrime[i] == false) {
                isNotPrime[i] = true;

                if (++deleteCount == K) {
                    System.out.println(i);
                    return;
                }

                for (int j = i * 2; j < N + 1; j = j + i) {

                    if (isNotPrime[j] == false) {
                        isNotPrime[j] = true;

                        if (++deleteCount == K) {
                            System.out.println(j);
                            return;
                        }
                    }
                }
            }

        }
    }

}
