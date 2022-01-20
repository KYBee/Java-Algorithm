package day04.p1991;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Integer> tree;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day04/p1991/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        tree = new ArrayList<>();

    }

}

class Node {

    char name;
    Node left;
    Node right;

    Node(char name, char left, char right) {

    }
}
