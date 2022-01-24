package day08.p1929;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 1000001;
    static int start, end;
    static boolean[] isNotPrime;
    static List<Integer> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day08/p1929/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        isNotPrime = new boolean[MAX_VALUE];
        getPrime();

        while (start <= end) {
            if (isNotPrime[start] == false) {
                System.out.println(start);
            }
            start++;
        }
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
