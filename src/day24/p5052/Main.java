package day24.p5052;

import java.io.*;
import java.util.*;

public class Main {

    static int TC, N;
    static final int NUM = 10;

    static Trie root;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day24/p5052/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        TC = Integer.parseInt(br.readLine());

        for (int i = 0; i < TC; i++) {

            N = Integer.parseInt(br.readLine());
            root = new Trie();
            String[] str = new String[N];
            for (int j = 0; j < N; j++) {
                str[j] = br.readLine();
                insert(str[j]);
            }

            boolean answer = true;
            for (int j = 0; j < N; j++) {
                if(!available(str[j])) {
                    answer = false;
                    break;
                }
            }
            if(answer) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb.toString());
    }

    static class Trie {
        boolean isEnd;
        Trie children[];

        Trie() {
            isEnd = false;
            children = new Trie[NUM];
            for (int i = 0; i < NUM; i++) {
                children[i] = null;
            }
        }
    }

    static void insert(String key) {
        Trie currentTrie = root;

        int length = key.length();
        int level;
        int target;

        for (level = 0; level < length; level++) {
            target = key.charAt(level) - '0';

            if (currentTrie.children[target] == null) {
                currentTrie.children[target] = new Trie();
            }
            currentTrie = currentTrie.children[target];
        }
        currentTrie.isEnd = true;
    }

    static boolean available(String key) {
        Trie currentTrie = root;
        int length = key.length();
        int level;
        int target;

        for (level = 0; level < length; level++) {
            target = key.charAt(level) - '0';
            if (currentTrie.isEnd) {
                return false;
            }
            currentTrie = currentTrie.children[target];
        }
        return true;
    }
}

