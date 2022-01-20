package day03.p10845;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Queue queue = new Queue();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day03/p10845/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        int num;

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("push")) {
                num = Integer.parseInt(st.nextToken());

                queue.command(num);

            } else {

                int result = queue.command(command);
                System.out.println(result);
            }
        }
    }
}


class Queue {

    int size;
    int front;
    int back;
    List<Integer> queue = new LinkedList<>();

    Queue() {
        size = 0;
        front = -1;
        back = -1;
    }

    public void command(int num) {
        if(queue.isEmpty()) {
            front++;
        }

        queue.add(num);
        size++;
        back++;
    }

    public int command(String command) {
        switch(command) {
            case "pop":
                return this.pop();
            case "size":
                return this.size();
            case "empty":
                return this.empty();
            case "front":
                return this.front();
            case "back":
                return this.back();
            default:
                return -1;
        }
    }


    int pop() {
        if(queue.isEmpty()) {
            return -1;
        }

        int delete = queue.remove(front);
        size--;
        back--;

        if(queue.isEmpty()) {
            front = -1;
            back = -1;
        }

        return delete;
    }


    int size() {
        return size;
    }


    int front() {
        if (queue.isEmpty()) return -1;
        return queue.get(front);
    }

    int back() {
        if (queue.isEmpty()) return -1;
        return queue.get(back);
    }

    int empty() {
        if (queue.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }
}