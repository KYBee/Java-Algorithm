package day14.p18870;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;
    static int[] finalList;
    static Map<Integer, Integer> dict = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day14/p18870/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        numList = new int[N];
        finalList = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
            finalList[i] = numList[i];
        }

        Arrays.sort(numList);
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (!dict.containsKey(numList[i])) {
                dict.put(numList[i], idx++);
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(dict.get(finalList[i])).append(" ");
        }

        System.out.println(sb.toString());

    }
}
