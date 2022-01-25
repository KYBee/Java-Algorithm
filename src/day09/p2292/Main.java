package day09.p2292;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day09/p2292/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int num = 1;
        int diff = 6;
        int count = 1;

        while (num < n) {
            num += diff * count++;
        }

        System.out.println(count);
    }
}
