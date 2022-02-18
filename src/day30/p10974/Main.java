package day30.p10974;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;
    static int[] output;
    static Set<String> result = new HashSet<>();

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day30/p10974/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        numList = new int[N];
        output = new int[N];

        for (int i = 0; i < N; i++) {
            numList[i] = i + 1;
        }

        permutation(numList, output, new boolean[N], 0, N, N);
        
        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        for (int i = 0; i < answer.size(); i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(answer.get(i).charAt(j) + " ");
            }
            System.out.println();
        }
    }

    public static void permutation(int[] arr, int[] output, boolean[] visited, int start, int n, int r) {

        if (start == r){
            String str = "";
            for (int i = 0; i < r; i++) {
                str += output[i];
            }

            result.add(str);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                output[start] = arr[i];
                permutation(arr, output, visited, start + 1, n, r);
                output[start] = 0;
                visited[i] = false;
            }
        }
        
    }
}
