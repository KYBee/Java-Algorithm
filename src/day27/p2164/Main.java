package day27.p2164;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day27/p2164/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        int before = q.poll();
        while (!q.isEmpty()) {

            before = q.poll();
            q.add(before);

            before = q.poll();
        }

        System.out.println(before);
    }
}
