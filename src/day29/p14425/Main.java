package day29.p14425;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static Trie root = new Trie();
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day29/p14425/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            insert(input);
        }

        for (int i = 0; i < M; i++) {
            String input = br.readLine();

            if (available(input)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static void insert(String str) {
        Trie currentTrie = root;

        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';

            if (currentTrie.children[index] == null) {
                currentTrie.children[index] = new Trie();
            }
            currentTrie = currentTrie.children[index];
        }
        currentTrie.isLast = true;
    }

    public static boolean available(String str) {
        Trie currentTrie = root;

        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';

            if (currentTrie.children[index] == null) {
                return false;
            }

            currentTrie = currentTrie.children[index];
        }

        if (currentTrie.isLast) {
            return true;
        } else {
            return false;
        }
    }
}

class Trie {
    boolean isLast;
    Trie[] children;

    public Trie() {
        this.children = new Trie[26];
        Arrays.fill(this.children, null);
    }
}

