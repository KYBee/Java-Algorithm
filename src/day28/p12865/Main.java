package day28.p12865;

import java.util.*;
import java.io.*;

public class Main {

    static int N, K;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day28/p12865/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

    }
}
