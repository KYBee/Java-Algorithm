package day09.p2941;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static String word;
    static int total;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day09/p2941/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        word = st.nextToken();
        int length = word.length();

        for (int i = 0; i < length; i++) {

            if (word.charAt(i) == 'c' && i < length - 1) {
                if (word.charAt(i + 1) == '=') {
                    i++;
                } else if (word.charAt(i + 1) == '-') {
                    i++;
                }
            } else if (word.charAt(i) == 'd'&& i < length - 1) {
                if (word.charAt(i + 1) == '-') {
                    i++;
                } else if (word.charAt(i + 1) == 'z' && i < length - 2 && word.charAt(i + 2) == '=') {
                    i += 2;
                }
            } else if (word.charAt(i) == 'l'&& i < length - 1) {
                if (word.charAt(i + 1) == 'j') {
                    i++;
                }
            } else if (word.charAt(i) == 'n'&& i < length - 1) {
                if (word.charAt(i + 1) == 'j') {
                    i++;
                }
            } else if (word.charAt(i) == 's'&& i < length - 1) {
                if (word.charAt(i + 1) == '=') {
                    i++;
                }
            } else if (word.charAt(i) == 'z'&& i < length - 1) {
                if (word.charAt(i + 1) == '=') {
                    i++;
                }
            }

            total++;
        }


        System.out.println(total);

    }

}
