package day14.p1037;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static List<Integer> numList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day14/p1037/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList.add(Integer.parseInt(st.nextToken()));
        }

        int biggest = Collections.max(numList);
        int smallest = Collections.min(numList);

        System.out.println(biggest * smallest);

    }
}
