package day30.p11047;

import java.util.*;
import java.io.*;

public class Main {
    
    static int N, K;
    static int[] coin;

    public static void main(String[] args) throws Exception {
        
        System.setIn(new FileInputStream("src/day30/p11047/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coin = new int[N];

        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coin);

        int answer = 0;
        for (int i = coin.length - 1; i >= 0; i--) {
            answer += (int) K / coin[i];
            K = K % coin[i];
        }

        System.out.println(answer);
    }
}
