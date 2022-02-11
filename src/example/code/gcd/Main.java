package example.code.gcd;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/example/code/gcd/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        System.out.println(gcd(M, N));
    }

    //ab -> cd
    public static int gcd(int a, int b) {
        int r = a % b;

        while (r != 0) {
            r = a % b;
            a = b;
            b = r;
        }

        return a;
    }
}
