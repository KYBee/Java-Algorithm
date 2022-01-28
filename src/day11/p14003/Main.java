package day11.p14003;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;
    static int[] biggestList;
    static int total = 1;
    static int INF = Integer.MIN_VALUE;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day11/p14003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        numList = new int[N + 1];
        biggestList = new int[N + 1];

        Arrays.fill(biggestList, INF);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }
        biggestList[1] = numList[1];


        for (int i = 2; i <= N; i++) {

            if (biggestList[total - 1] == numList[i]) {
                continue;
            }
            else if (biggestList[total] < numList[i]) {
                biggestList[++total] = numList[i];
            } else {
                int idx = binarySearch(numList[i], 1, total - 1);
                biggestList[idx] = numList[i];
            }

            //System.out.println(Arrays.toString(biggestList));
        }

        bw.write(total + "\n");
        for (int i = 1; i <= total; i++) {
            bw.write(biggestList[i] + " ");
        }
        bw.write('\n');

        bw.flush();
        bw.close();
    }

    public static int binarySearch(int target, int start, int end) {

        while (start <= end) {
            int mid = (start + end) / 2;

            if (biggestList[mid] == target) {
                return mid;
            } else if (biggestList[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return 0;
    }
}
