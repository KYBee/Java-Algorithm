package day06.p5622;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day06/p5622/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int total = 0;
        char[] dial = st.nextToken().toCharArray();

        for (int i = 0; i < dial.length; i++) {
            char current = dial[i];

            switch(current) {
                case 'A':
                case 'B':
                case 'C':
                    total += 3;
                    break;
                case 'D':
                case 'E':
                case 'F':
                    total += 4;
                    break;
                case 'G':
                case 'H':
                case 'I':
                    total += 5;
                    break;
                case 'J':
                case 'K':
                case 'L':
                    total += 6;
                    break;
                case 'M':
                case 'N':
                case 'O':
                    total += 7;
                    break;
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                    total += 8;
                    break;
                case 'T':
                case 'U':
                case 'V':
                    total += 9;
                    break;
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    total += 10;
                    break;
                default:
                    return;
            }
        }

        System.out.println(total);
    }
}
