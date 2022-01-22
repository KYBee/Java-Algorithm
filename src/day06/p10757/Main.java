package day06.p10757;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static char[] num1;
    static char[] num2;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day06/p10757/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        num1 = st.nextToken().toCharArray();
        num2 = st.nextToken().toCharArray();

    }


}
