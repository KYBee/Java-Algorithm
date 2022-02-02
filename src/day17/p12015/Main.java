package day17.p12015;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;
    static List<Integer> dp = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day17/p12015/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        numList = new int[N];

        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        dp.add(numList[0]);
        // idx 는 0, dp에는 가장 작은 값들이 한 개씩 들어갈 예정

        for (int i = 1; i < N; i++) {

            if (numList[i] == dp.get(dp.size() - 1)) {
                continue;
            } else if (numList[i] > dp.get(dp.size() - 1)) {
                dp.add(numList[i]);
            } else {
                int newIdx = upperBound(numList[i]);
                dp.set(newIdx, numList[i]);
            }

        }

        System.out.println(dp.size());
    }

    public static int upperBound(int target) {
        int start = 0, end = dp.size();

        while (start < end) {
            int mid = (start + end) / 2;

            if (target <= dp.get(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}
