package day08.p1427;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static char[] number;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day08/p1427/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        number = st.nextToken().toCharArray();

        Arrays.sort(number);

        for (int i = number.length - 1; i >= 0; i--) {
            sb.append(number[i]);
        }

        System.out.println(sb.toString());
    }
}
