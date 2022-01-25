package day09.p10757;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.util.Comparator.*;

public class Main {

    static char[] num1, num2;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day09/p10757/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        num1 = new StringBuilder(st.nextToken()).reverse().toString().toCharArray();
        num2 = new StringBuilder(st.nextToken()).reverse().toString().toCharArray();

        StringBuilder result = new StringBuilder();

        int length = Math.min(num1.length, num2.length);
        int fromBottom = 0;
        int numSum = 0;

        for (int i = 0; i < length; i++) {
            int a = num1[i] - '0';
            int b = num2[i] - '0';

            numSum = a + b + fromBottom;
            result.append(numSum % 10);
            fromBottom =  numSum / 10;
        }

        if (num1.length > num2.length) {
            for (int i = length; i < num1.length; i++) {
                numSum = num1[i] - '0' + fromBottom;
                result.append(numSum % 10);
                fromBottom = numSum / 10;
            }

        } else {
            for (int i = length; i < num2.length; i++) {
                numSum = num2[i] - '0' + fromBottom;
                result.append(numSum % 10);
                fromBottom = numSum / 10;
            }
        }

        if (fromBottom !=  0) {
            result.append(fromBottom);
        }

        System.out.println(result.reverse().toString());
    }
}
