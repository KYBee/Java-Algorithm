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
    static Node root;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day04/p1991/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        char current = st.nextToken().charAt(0);
        root = new Node(current);

        char left = st.nextToken().charAt(0);
        char right = st.nextToken().charAt(0);
        if (left != '.') {
            root.left = new Node(left);
        }
        if (right != '.') {
            root.right = new Node(right);
        }

        Node currentNode = root;

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            current = st.nextToken().charAt(0);
            left = st.nextToken().charAt(0);
            right = st.nextToken().charAt(0);
            getNode(root, current, left, right);
        }

        preorder(root);
        System.out.println();
        inorder(root);
        System.out.println();
        postorder(root);
    }

    //해당 알파벳의 노드가 트리에 있는지 확인
    public static void getNode(Node n, char c, char left, char right) {

        if (n == null) {
            return;
        } else {

            if (n.data == c) {
                if (left != '.') {
                    n.left = new Node(left);
                }
                if (right != '.') {
                    n.right = new Node(right);
                }
            } else {
                getNode(n.left, c, left, right);
                getNode(n.right, c, left, right);
            }

        }
    }

    public static void preorder(Node n) {
        System.out.print(n.data);
        if (n.left != null) {
            preorder(n.left);
        }
        if (n.right != null) {
           preorder(n.right);
        }
    }

    public static void inorder(Node n) {
        if (n.left != null) {
            inorder(n.left);
        }
        System.out.print(n.data);
        if (n.right != null) {
            inorder(n.right);
        }
    }

    public static void postorder(Node n) {
        if (n.left != null) {
            postorder(n.left);
        }
        if (n.right != null) {
            postorder(n.right);
        }
        System.out.print(n.data);
    }

}

class Node {
    char data;
    Node left;
    Node right;

    Node(char data) {
        this(data, null, null);
    }

    Node(char data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
