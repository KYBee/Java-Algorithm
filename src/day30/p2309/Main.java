package day30.p2309;

import java.util.*;
import java.io.*;

public class Main {

    static final int N = 9;
    static int[] height = new int[N];
    static int diff = 0;

    static Set<Integer> heightSet = new HashSet<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day30/p2309/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int total = 0;
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            total += h;
            height[i] = h;
        }

        diff = total - 100;
        combination(height, new boolean[N], 0, N, 2);

        List<Integer> answer = new ArrayList<>(heightSet);

        Collections.sort(answer);

        for (Integer integer : answer) {
            System.out.println(integer);
        }
    }

    public static boolean combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i] == true) {
                    sum += arr[i];
                }
            }

            if (diff == sum) {
                for (int i = 0; i < n; i++) {
                    if (visited[i] == false) {
                        heightSet.add(arr[i]);
                    }
                }
                return true;
            }

            return false;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            boolean found = combination(arr, visited, i + 1, n, r - 1);
            if (found) {
                return true;
            }
            visited[i] = false;
        }

        return false;
    }

}
