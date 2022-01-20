package day03.p10828;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static Stack stack = new Stack();
    static int N;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day03/p10828/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int num;

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("push")) {
                num = Integer.parseInt(st.nextToken());

                stack.command(num);

            } else {

                int result = stack.command(command);
                System.out.println(result);
            }

        }
    }

}


class Stack {

    List<Integer> stack = new LinkedList<>();
    int size;
    int top;

    Stack() {
        size = 0;
        top = -1;
    }

    public void command(int num) {
        stack.add(num);
        size++;
        top++;
    }

    public int command(String command) {
        switch(command) {
            case "pop":
                return this.pop();
            case "size":
                return this.size();
            case "empty":
                return this.empty();
            case "top":
                return this.top();
            default:
                return -1;
        }
    }


    int pop() {
        if(stack.isEmpty()) {
            return -1;
        }

        int delete = stack.remove(top--);
        size--;

        return delete;
    }


    int size() {
        return size;
    }


    int top() {
        if (stack.isEmpty()) return -1;
        return stack.get(top);
    }

    int empty() {
        if (stack.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }

}