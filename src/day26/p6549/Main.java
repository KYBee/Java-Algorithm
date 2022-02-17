//TODO : 해결하지 못함

package day26.p6549;

import java.io.*;
import java.util.*;

public class Main {

    static String[] stringList;
    static int[] numList;
    static int[] tree;
    static int N, S;

    static long answer = 0;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day26/p6549/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String input = br.readLine();
            if (input.equals("0")) {
                break;
            }

            stringList = input.split(" ");
            N = stringList.length;
            S = 1;

            while (S < N) {
                S *= 2;
            }

            numList = new int[N + 1];
            numList[0] = Integer.MAX_VALUE;
            tree = new int[S * 2];

            // tree leaf에는 자기 자신의 index
            for (int i = 0; i < N; i++) {
                tree[S + i] = i + 1;
                numList[i + 1] = Integer.parseInt(stringList[i]);
            }

            // bottom up 하면서 범위 내에서 더 작은 높이의 인덱스 적용
            for (int i = S - 1; i > 0; i--) {
                if (numList[(int)tree[i * 2]] < numList[(int)tree[i * 2 + 1]]) {
                    //child node 중 더 작은 것의 index를 지정함
                    tree[i] = tree[i * 2];
                } else {
                    tree[i] = tree[i * 2 + 1];
                }
            }

            //Debugging
            System.out.println(Arrays.toString(tree));

            answer = getArea(1, S);


        }
    }

    public static int query(int left, int right, int node, int leftQuery, int rightQuery) {
        if (right < leftQuery || rightQuery < left) {
            return 0;
        } else if (leftQuery <= left && right <= rightQuery) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            int leftIndex = query(left, mid, node * 2, leftQuery, rightQuery);
            int rightIndex = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);

            if (numList[leftIndex] < numList[rightIndex]) {
                return leftIndex;
            } else {
                return rightIndex;
            }
        }
    }

    public static long getArea(int left, int right) {
        int index = query(1, S, 1, left, right);
        long area = (long) (left - right + 1) * (long) numList[index];
        long tempArea = 0;

        if (left <= index - 1) {
            tempArea = Math.max(tempArea, getArea(left, index - 1));
        }
        if (index + 1 <= right) {
            tempArea = Math.max(tempArea, getArea(index + 1, right));
        }

        return Math.max(area, tempArea);
    }
}
