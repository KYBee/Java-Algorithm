package day05.p3995;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long A, B;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day05/p3995/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {

            // X: 인당 나누어 줄 사탕의 수
            // Y: 사탕 봉지의 수
            // A * x + 1 = B * Y
            // A x + B y = C 형태로 변환
            // -A x + B y = 1
            // A(-x) + By = 1의 형태로 변환

            st = new StringTokenizer(br.readLine());
            A = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());

            // 1. 해 검증
            // D  ->  gcd(A, B)
            // Ax + By = C일 때 C % D == 0 이어야 해가 나옴 : 베주 항등식
            // C % D != 0 해가 없음

            EGResult result = extendedGcd(A, B);
            long D = result.r;

            if (result.r != 1) {
                System.out.println("IMPOSSIBLE");
            } else {

                // 2. 초기 해 구하기
                // x0 = s * C / D
                // y0 = t * C / D

                long x0 = result.s;
                long y0 = result.t;

                // 3. 일반해 구하기
                // x = x0 + B / D * K
                // y = y0 - A / D * K


                // 4. K 의 범위
                // x < 0
                // x0 + B * K < 0
                // K < -x0 / B


                // 0 < y < 10 ^ 9
                // 0 < y0 - A * K <= 10 ^ 9
                // -y0 < -A * k <= 10 ^ 9 - y0
                // (y0 - 10^9) / A <= K < y0 / A


                // K < -x0 / B
                // (y0 - 10^9) / A <= K < y0 / A


                // 경계를 찾을 때 -> 올림하고 -1 하거나 버림하거나
                // 등호가 있으면 버림 가능. 등호가 없으면 올림하고 -1 해야 함


                long kFromX = (long) Math.ceil((double) -x0 / (double) B) - 1;
                long kFromY = (long) Math.ceil((double) y0 / (double) A) - 1;
                long k = Math.min(kFromX, kFromY);

                long kLimitFromY = (long) Math.ceil((y0 - 1e9) / (double) A);

                if (kLimitFromY <= k) {
                    System.out.println(y0 - A * k);
                } else {
                    System.out.println("IMPOSSIBLE");
                }

            }
        }



    }

    static EGResult extendedGcd(long a, long b) {
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        long temp;
        while(r1 != 0) {
            long q = r0 / r1;

            temp = r0 - q * r1;
            r0 = r1;
            r1 = temp;

            temp = s0 - q * s1;
            s0 = s1;
            s1 = temp;

            temp = t0 - q * t1;
            t0 = t1;
            t1 = temp;
        }

        return new EGResult(s0, t0, r0);
    }

}

class EGResult {
    long s;
    long t;
    long r;

    public EGResult(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }

    @Override
    public String toString() {
        return "EGResult{" +
                "s=" + s +
                ", t=" + t +
                ", r=" + r +
                '}';
    }
}
