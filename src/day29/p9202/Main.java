package day29.p9202;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
    static final int[] dy = {0, 0, -1, 1, -1, -1, 1, 1};

    static int N, M;
    static Trie root = new Trie();
    static char[][] map;

    //static String result;
    //static int score;
    //static int count;

    static Set<String> result;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day29/p9202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            insert(input);
        }
        br.readLine();

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            map = new char[4][4];
            result = new HashSet<>();
            //initTrie();

            for (int j = 0; j < 4; j++) {
                String input = br.readLine();

                for (int k = 0; k < 4; k++) {
                    map[j][k] = input.charAt(k);
                }
            }
            br.readLine();

            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    dfs(j, k, new boolean[4][4], "");
                }
            }

            int count = result.size();
            int score = 0;
            String word = "";

            for (String s : result) {
                if (word.equals("")) {
                    word = s;
                } else {
                    word = getString(word, s);
                }
                score += getScore(s.length());
            }
            System.out.print(score + " " + word + " " + count + "\n");
        }

        //System.out.print(sb);
    }

    public static String getString(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return s2;
        } else if (s1.length() > s2.length()) {
            return s1;
        } else {
            if (s1.compareTo(s2) <= 0) {
                return s1;
            } else {
                return s2;
            }
        }
    }

    public static int getScore(int n) {
        switch (n) {
            case 3:
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 5;
            case 8:
                return 11;
            default:
                return 0;
        }
    }

    public static void dfs(int x, int y, boolean[][] visited, String str) {
        visited[x][y] = true;
        String current = str + map[x][y];

        int isWord = available(current);

        if (isWord == 2 && current.length() > 8) {
            result.add(current);
            return;
        } else if (isWord == 2) {
            result.add(current);
        }

        if (isWord > 0) {
            for (int i = 0; i < 8; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (0 <= newX && newX < 4 && 0 <= newY && newY < 4) {
                    if (!visited[newX][newY]) {
                        dfs(newX, newY, visited, current);
                    }
                }
            }
        }

        visited[x][y] = false;
    }

    public static void insert(String str) {
        Trie currentTrie = root;

        for (int i = 0; i < str.length(); i++) {
            int target = str.charAt(i) - 'A';
            if (currentTrie.children[target] == null) {
                currentTrie.children[target] = new Trie();
            }
            currentTrie = currentTrie.children[target];
        }

        currentTrie.isLast = true;
    }

    // 0 -> 불가능 1 -> 갈 수 있음 2 -> 존재하는 단어
    public static int available(String str) {
        Trie currentTrie = root;

        for (int i = 0; i < str.length(); i++) {
            int target = str.charAt(i) - 'A';
            if (currentTrie.children[target] == null) {
                return 0;
            }
            currentTrie = currentTrie.children[target];
        }

        if (currentTrie.isLast && !currentTrie.isHit) {
            //currentTrie.isHit = true;
            return 2;
        } else if (currentTrie.isLast && currentTrie.isHit){
            return 0;
        } else {
            return 1;
        }
    }

    public static void initTrie() {
        Queue<Trie> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Trie current = q.poll();
            current.isHit = false;

            for (int i = 0; i < 26; i++) {
                if (current.children[i] != null) {
                    q.add(current.children[i]);
                }
            }
        }
    }
}

class Trie {
    Trie[] children;
    boolean isLast;
    boolean isHit;

    Trie() {
        this.children = new Trie[26];
    }
}