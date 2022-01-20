package day04.p1735;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static Fraction[] f = new Fraction[2];

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day04/p1735/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            int nom = Integer.parseInt(st.nextToken());
            int denom = Integer.parseInt(st.nextToken());

            f[i] = new Fraction(nom, denom);
        }

        int totalDenom = f[0].denom * f[1].denom;
        int totalNom = f[0].nom * f[1].denom + f[1].nom * f[0].denom;
        int gcd = GCD(totalDenom, totalNom);


        System.out.format("%d %d", totalNom / gcd, totalDenom / gcd);

    }


    public static int GCD(int a, int b) {
        int r = a % b;
        while (r != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

}

class Fraction{
    int nom;
    int denom;

    public Fraction(int nom, int denom) {
        this.nom = nom;
        this.denom = denom;
    }
}