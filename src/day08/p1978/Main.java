package day08.p1978;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 1001;
    static int N;
    static boolean[] isNotPrime;
    static int count = 0;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day08/p1978/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        isNotPrime = new boolean[MAX_VALUE];
        getPrime();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int currentNumber = Integer.parseInt(st.nextToken());
            if(isNotPrime[currentNumber] == false) {
                count++;
            }
        }
        System.out.println(count);

    }

    public static void getPrime() {
        isNotPrime[1] = true;
        for (int i = 2; i < MAX_VALUE; i++) {
            if(isNotPrime[i] == false) {
                for (int j = i * 2; j < MAX_VALUE; j = j + i) {
                    isNotPrime[j] = true;
                }
            }
        }
    }


}
