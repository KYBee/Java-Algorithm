package day31.p11399;

import java.util.*;
import java.io.*;


public class Main {

    static int N;
    static int[] numList;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day31/p11399/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        N = Integer.parseInt(br.readLine());
        numList = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numList);

        int total = 0;
        int term = 0;
        for (int i = 0; i < N; i++) {
            total += (N - i) * numList[i];
        }

        System.out.println(total);
    }
}
