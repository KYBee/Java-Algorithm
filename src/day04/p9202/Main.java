package day04.p9202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int w;
    static int b;

    static char[][] map = new char[4][4];
    static Node root;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day04/p9202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());


        //trie 구성하기

        root = new Node('0');

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            String word = st.nextToken();
            Node current = root;

            for (int j = 0; j < word.length(); j++) {

                Node existNode = isNode(current, word.charAt(j));

                if (existNode != null) {
                    //만약 j 가 마지막 단어이면 체크
                    current = existNode;
                } else {
                    // 없으면 새로 노드를 만들어서 연결하기
                    Node newNode = new Node(word.charAt(j));
                    current.children.add(newNode);
                    current = newNode;
                }

                if (j == word.length() - 1) {
                    //마지막 단어이므로 isEnd바꿈
                    current.isEnd = true;
                }

            }
        }


        //Map 구성하기

        st = new StringTokenizer(br.readLine());
        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken());

        for (int i = 0; i < b; i++) {

            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                String line = st.nextToken();
                for (int k = 0; k < 4; k++) {
                    map[j][k] = line.charAt(k);
                }
            }

            if (i != b - 1) {
                st = new StringTokenizer(br.readLine());
            }

            //Debugging
            for (int j = 0; j < 4; j++) {
                System.out.println(Arrays.toString(map[j]));
            }
            System.out.println();
        }


        //TODO: DFS로 찾기
        /**
         * 1. 체크인
         * 2. 목적지인가?
         * 3. 인접한 노드 찾기
         * 4. 갈 수 있는가?
         * 5. 간다
         * 6. 체크아웃
         * */

    }

    public static void DFS(int x, int y, Node current, char[][] flag) {

        //체크인
        flag[x][y] = 'x';
        //목적지인가?
        if (current.isEnd) {
            current.isHit = true;
        }

        //인접한 노드 찾기;
        char c = map[x][y];

        List<Node> neighbors = new ArrayList<>();
        for (int i = 0; i < current.children.size(); i++) {

            if (current.children.get(i).data == c) {

            }

        }


    }

    public static Node isNode (Node n, char a) {
        for (Node child: n.children) {
            if (child.data == a) {
                return child;
            }
        }
        return null;
    }
}


class Node {

    char data;
    List<Node> children = new ArrayList<>();

    Boolean isEnd;
    Boolean isHit;

    public Node(char data) {
        this.data = data;
        this.isEnd = false;
        this.isHit = false;
    }

    public void clearHit() {
        isHit = false;
    }

    public void end() {
        isEnd = true;
    }

    @Override
    public String toString() {
        return "Node data = " + data;
    }

    @Override
    public boolean equals(Object o) {
        Node n = (Node) o;
        if (this.data == n.data) {
            return true;
        } else {
            return false;
        }
    }
}
