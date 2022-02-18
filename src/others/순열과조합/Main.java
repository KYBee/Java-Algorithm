package others.순열과조합;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;
    static int[] output;

    static Set<String> permuResult = new HashSet<>();
    static Set<String> combiResult = new HashSet<>();

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/others/순열과조합/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        numList = new int[N];
        output = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        permutation(numList, output, new boolean[N], 0, N, 2);
        combination(numList, new boolean[N], 0, N, 2);

        System.out.println(permuResult);
        System.out.println(combiResult);
    }

    public static void permutation(int[] arr, int[] output, boolean[] visited, int start, int n, int r) {
        if (start == r) {
            String str = "";
            for (int i = 0; i < r; i++) {
                str += output[i];
            }
            permuResult.add(str);
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

    public static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            String str = "";
            for (int i = 0; i < n; i++) {
                if(visited[i] == true) {
                    str += arr[i];
                }
            }

            combiResult.add(str);
            return;
        }

        for (int i = start; i < n; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                combination(arr, visited, i + 1, n, r - 1);
                visited[i] = false;
            }
        }
    }
}
