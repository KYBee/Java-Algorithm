package day05.p1837;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 1000000;
    static char[] P;
    static int K;
    static boolean[] isNotPrime = new boolean[MAX + 1];
    static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day05/p1837/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());

        isPrime();

        for (int prime : primes) {
            if(prime >= K) {
                break;
            }
            if(checkIsBad(prime)) {
                System.out.println("BAD "+prime);
                return;
            }
        }
        System.out.println("GOOD");
    }

    public static void isPrime() {
        for (int i = 2; i < isNotPrime.length; i++) {
            if (isNotPrime[i] == false) {
                primes.add(i);
                for (int j = 2 * i; j < isNotPrime.length; j = j + i) {
                    isNotPrime[j] = true;
                }
            }
        }
    }

    //BIGINTEGER 한번씩 다뤄보기
    static boolean checkIsBad(int x) {
        int r = 0;
        for (int i = 0; i < P.length; i++) {
            r = (r * 10 + (int) (P[i] - '0')) % x;
        }

        if (r == 0) {
            return true;
        } else {
            return false;
        }
    }

}
