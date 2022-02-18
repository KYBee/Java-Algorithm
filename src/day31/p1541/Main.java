package day31.p1541;

import java.util.*;
import java.io.*;

public class Main {

    static Stack<Integer> number = new Stack<>();
    static Queue<String> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day31/p1541/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String equation = br.readLine();

        String item = "";
        int length = equation.length();

        for (int i = 0; i < length; i++) {
            if (equation.charAt(i) != '+' && equation.charAt(i) != '-') {
                item += equation.charAt(i);
            } else {
                q.add(item);
                q.add(Character.toString(equation.charAt(i)));
                item = "";
            }
        }
        q.add(item);

        number.push(Integer.parseInt(q.poll()));

        boolean minus = false;
        while (!q.isEmpty()) {
            char operator = q.poll().charAt(0);
            int n1 = Integer.parseInt(q.poll());

            if (operator == '-') {
                minus = true;
            }

            if (minus) {
                number.add(number.pop() - n1);
            } else {
                number.add(number.pop() + n1);
            }
        }

        System.out.println(number.pop());
    }
}
