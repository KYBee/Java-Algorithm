package day06.p1920;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static Set<Long> numbersN = new HashSet<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day06/p1920/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbersN.add(Long.parseLong(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            boolean current = numbersN.contains(Long.parseLong(st.nextToken()));

            if(current) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }

    }

}
