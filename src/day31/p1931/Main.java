package day31.p1931;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static List<Integer> occupied = new ArrayList<>();
    static PriorityQueue<Class> pq = new PriorityQueue<>();
            
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day31/p1931/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());

        int max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            max = Math.max(max, end);

            pq.add(new Class(start, end));
        }

        int answer = 1;
        Class current = pq.poll();
        int start = current.start;
        int end = current.end;

        while (!pq.isEmpty()) {
            current = pq.poll();

            if (end <= current.start) {
                answer += 1;
                start = current.start;
                end = current.end;
            }
        }
        System.out.println(answer);
    }
}

class Class implements Comparable<Class> {
    int start;
    int end;

    public Class(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Class c) {
        int comp = Integer.compare(end, c.end);
        if (comp == 0) {
            comp = Integer.compare(start, c.start);
        }
        return comp;
    }

    @Override
    public String toString() {
        return "Class{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}