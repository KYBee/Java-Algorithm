package day08.p2581;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 10001;
    static int start, end;
    static boolean[] isNotPrime;
    static int minPrime = 0;
    static List<Integer> result = new LinkedList<>();
    static int total = 0;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day08/p2581/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        end = Integer.parseInt(st.nextToken());

        isNotPrime = new boolean[MAX_VALUE];
        getPrime();


        while (start <= end) {
            if (isNotPrime[start] == false) {
                result.add(start);
                if(minPrime == 0) {
                    minPrime = start;
                }
                total+=start;
            }
            start++;
        }

        if (result.size() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(total);
            System.out.println(minPrime);
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
