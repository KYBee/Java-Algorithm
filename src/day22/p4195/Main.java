package day22.p4195;

import java.util.*;
import java.io.*;

public class Main {

    static int TC, R;
    static int[] parent;
    static int[] level;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day22/p4195/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < TC; i++) {
            R = Integer.parseInt(br.readLine());
            Map<String, Integer> dict = new HashMap<>();

            parent = new int[R * 2];
            level = new int[R * 2];


            for (int j = 0; j < 2 * R; j++) {
                parent[j] = j;
                level[j] = 1;
            }


            int count = 0;
            for (int j = 0; j < R; j++) {
                st = new StringTokenizer(br.readLine());

                String a = st.nextToken();
                String b = st.nextToken();

                if (!dict.containsKey(a)) {
                    dict.put(a, count++);
                }
                if (!dict.containsKey(b)) {
                    dict.put(b,  count++);
                }

                int aVal = dict.get(a);
                int bVal = dict.get(b);

                union(aVal, bVal);
                sb.append(level[find(aVal)] + "\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static void union(int from, int to) {
        int fromValue = find(from);
        int toValue = find(to);

        if (fromValue > toValue) {
            parent[fromValue] = toValue;
            level[toValue] += level[fromValue];
            level[fromValue] = 0;
        } else if (toValue > fromValue) {
            parent[toValue] = fromValue;
            level[fromValue] += level[toValue];
            level[toValue] = 0;
        }
    }

    public static int find(int target) {
        if (target == parent[target]) {
            return target;
        } else {
            return parent[target] = find(parent[target]);
        }
    }

}
