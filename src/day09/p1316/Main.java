package day09.p1316;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static String[] word;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day09/p1316/input5.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        word = new String[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            word[i] = st.nextToken();
        }

        for (int i = 0; i < n; i++) {
            List<Character> alphabet = new LinkedList<>();

            char before = 0;
            boolean isGroup = true;
            for (char c : word[i].toCharArray()) {

                if (before != c) {
                    if (alphabet.contains(c)) {
                        isGroup = false;
                        break;
                    } else {
                        alphabet.add(c);
                        before = c;
                    }
                }
            }

            if (isGroup) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}