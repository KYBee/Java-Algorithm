package day05.p9267;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long a, b, S;
    static long[] result = new long[3];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day05/p9267/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        S = Long.parseLong(st.nextToken());

        extendedGCD(a, b);
        long gcd = result[2];
        long x0 = result[0] * S / gcd;
        long y0 = result[1] * S / gcd;

        // C % D == 0 이 아니면 안됨
        if (S % gcd != 0) {
            System.out.println("NO");
            return;
        }


        long kMax = (long) (Math.floor(((double) y0 / (double) a)));
        long kMin = (long) Math.ceil(((double) x0 / (double) b) * -1);

        if (kMin <= kMax) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    public static void extendedGCD(long a, long b) {
        long s0, t0, r0;
        long s1, t1, r1;
        long q, temp;

        s0 = 1;
        t0 = 0;
        r0 = a;
        s1 = 0;
        t1 = 1;
        r1 = b;

        while (r1 != 0) {
            q = r0 / r1;

            temp = r0 - r1 * q;
            r0 = r1;
            r1 = temp;

            temp = s0 - s1 * q;
            s0 = s1;
            s1 = temp;

            temp = t0 - t1 * q;
            t0 = t1;
            t1 = temp;
        }

        result[0] = s0;
        result[1] = t0;
        result[2] = r0;
    }

}
