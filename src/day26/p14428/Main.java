package day26.p14428;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, S = 1;
    static Point[] tree;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day26/p14428/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        while (S < N) {
            S *= 2;
        }

        tree = new Point[S * 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long number = Long.parseLong(st.nextToken());
            tree[S + i] = new Point(i + 1, number);
        }
        for (int i = N; i < S; i++) {
            tree[S + i] = new Point(i + 1, INF);
        }
        for (int i = S - 1; i > 0; i--) {
            tree[i] = Point.min(tree[i * 2] , tree[i * 2 + 1]);
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (op == 2) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                sb.append(query(1, S, 1, a, b).index).append("\n");
            } else {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                update(1, S, 1, a, b);
            }
        }

        System.out.print(sb);
    }

    public static Point query(int left, int right, int node, int leftQuery, int rightQuery) {
        if (rightQuery < left || right < leftQuery) {
            return new Point(0, INF);
        } else if (leftQuery <= left & right <= rightQuery) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            Point leftPoint = query(left, mid, node * 2, leftQuery, rightQuery);
            Point rightPoint = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return Point.min(leftPoint, rightPoint);
        }
    }

    public static Point update(int left, int right, int node, int target, long value) {
        if (target < left || right < target) {
            return tree[node];
        } else {
            if (left == right) {
                tree[node].data = value;
                return tree[node];
            }

            int mid = (left + right) / 2;
            Point leftPoint = update(left, mid, node * 2, target, value);
            Point rightPoint = update(mid + 1, right, node * 2 + 1, target, value);

            return tree[node] = Point.min(leftPoint, rightPoint);
        }
    }
}

class Point {
    long data;
    int index;

    public Point(int index, long data) {
        this.data = data;
        this.index = index;
    }

    public static Point min(Point p1, Point p2) {
        int comp = Long.compare(p1.data, p2.data);
        if (comp == 0) {
            comp = Integer.compare(p1.index, p2.index);
        }
        if (comp <= 0) {
            return p1;
        } else {
            return p2;
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "data=" + data +
                ", index=" + index +
                '}';
    }
}