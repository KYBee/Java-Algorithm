package day08.p11650;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Point[] points;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day08/p11650/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        points = new Point[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        Arrays.sort(points);
        for (Point p : points) {
            sb.append(p.x).append(" ").append(p.y).append("\n");
        }

        System.out.println(sb.toString());
    }
}

class Point implements Comparable<Point> {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Point o) {
        int comp = this.x - o.x;
        if (comp == 0) {
            return this.y - o.y;
        }
        return comp;
    }
}
