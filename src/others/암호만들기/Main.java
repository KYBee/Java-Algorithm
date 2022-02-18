package others.암호만들기;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static char[] charList;
    static Set<String> result = new HashSet<>();

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/others/암호만들기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        charList = new char[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            charList[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(charList);

        combination(charList, new boolean[N], 0, N, M, 0, 0);

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        for (String s : answer) {
            System.out.println(s);
        }
    }

    public static void combination(char[] arr, boolean[] visited, int start, int n, int r, int jaum, int moum) {

        if (r == 0) {
            if (jaum >= 2 && moum >= 1) {
                String str = "";
                for (int i = 0; i < n; i++) {
                    if (visited[i] == true) {
                        str += arr[i];
                    }
                }

                result.add(str);
            }
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;

            if (isMoum(arr[i])) {
                combination(arr, visited, i + 1, n, r - 1, jaum, moum + 1);
            } else {
                combination(arr, visited, i + 1, n, r - 1, jaum + 1, moum);
            }
            visited[i] = false;
        }

    }

    public static boolean isMoum(char c) {
        switch(c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }

}
