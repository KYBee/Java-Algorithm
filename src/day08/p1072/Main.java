package day08.p1072;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    static long X, Y, Z;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day08/p1072/input5.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());

        Z = getPercent(X, Y);

        if (Z >= 99) {
            System.out.println(-1);
        } else {

            long start = 0;
            long end = 2000000000;
            while (start < end) {
                long mid = (start + end) / 2;
                long newZ = 100 * (Y + mid) / (X + mid);

                if (newZ > Z) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }

            System.out.println(end);
        }

    }

    public static long getPercent(long x, long y) {
        return Y * 100 / X;
    }

}
