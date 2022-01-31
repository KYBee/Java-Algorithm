package day15.p2981;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day15/p2981/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());

        numList = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numList[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numList);

        int gcdValue = numList[1] - numList[0];

        for (int i = 2; i < N; i++) {
            gcdValue = gcd(gcdValue, numList[i] - numList[i - 1]);
        }

        for (int i = 2; i <= gcdValue; i++) {
            if (gcdValue % i == 0) {
                System.out.print(i + " ");
            }
        }
    }

    public static int gcd(int n, int m) {
        int r = n % m;

        while (r != 0) {
            n = m;
            m = r;
            r = n % r;
        }

         return m;
    }
}
