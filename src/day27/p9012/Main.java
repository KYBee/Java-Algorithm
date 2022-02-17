package day27.p9012;

import java.util.*;
import java.io.*;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day27/p9012/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            Stack<Character> stack = new Stack<>();
            String input = br.readLine();

            int length = input.length();
            int index = 0;
            boolean isVPS = true;

            while (index < length) {
                if (input.charAt(index) == '(') {
                    stack.push('(');
                } else {
                    if (stack.isEmpty()) {
                        isVPS = false;
                        break;
                    }
                    stack.pop();
                }
                index++;
            }

            if (isVPS && stack.isEmpty()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
