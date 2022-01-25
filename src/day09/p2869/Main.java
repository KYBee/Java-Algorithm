package day09.p2869;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static double A, B, V;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day09/p2869/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Double.parseDouble(st.nextToken());
        B = Double.parseDouble(st.nextToken());
        V = Double.parseDouble(st.nextToken()) - A;

        double day = V / (A - B);
        int result = (int) Math.ceil(day);

        System.out.println(++result);

    }
}
