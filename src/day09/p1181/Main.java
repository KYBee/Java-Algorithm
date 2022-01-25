package day09.p1181;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static PriorityQueue <String> dict = new PriorityQueue<>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int comp = o1.length() - o2.length();
            if (comp == 0) {
                return o1.compareTo(o2);
            }
            return comp;
        }
    });
    static int N;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day09/p1181/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String word = st.nextToken();
            if (! dict.contains(word)) {
                dict.add(word);
            }
        }

        while (dict.isEmpty() == false) {

            System.out.println(dict.poll());
        }
    }
}
