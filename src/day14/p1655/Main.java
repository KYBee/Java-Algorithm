package day14.p1655;

import java.util.*;
import java.io.*;

public class Main {

    static int N;

    static PriorityQueue<Integer> leftHeap = new PriorityQueue(Comparator.reverseOrder());
    static PriorityQueue<Integer> rightHeap = new PriorityQueue();

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day14/p1655/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int leftLength = 0, rightLength = 0;
        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            int data = Integer.parseInt(st.nextToken());

            leftLength = leftHeap.size();
            rightLength = rightHeap.size();

            if (leftLength == rightLength) {
                leftHeap.add(data);

                if(!rightHeap.isEmpty() && rightHeap.peek() < leftHeap.peek()) {
                    rightHeap.add(leftHeap.poll());
                    leftHeap.add(rightHeap.poll());
                }
            } else {
                rightHeap.add(data);

                if(rightHeap.peek() < leftHeap.peek()) {
                    rightHeap.add(leftHeap.poll());
                    leftHeap.add(rightHeap.poll());
                }
            }

            sb.append(leftHeap.peek()).append("\n");
        }

        System.out.println(sb.toString());
    }
}
