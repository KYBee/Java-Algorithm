package day06.p5622;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day06/p5622/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] dial = st.nextToken().toCharArray();
        Arrays.sort(dial);

        System.out.println(Arrays.toString(dial));

    }
}
