package day29.p4358;

import java.util.*;
import java.io.*;

public class Main {

    static Set<String> tree = new HashSet<>();
    static Trie root = new Trie();
    static Map<Character, Integer> dict = new HashMap<>();

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day29/p4358/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int index = 0;
        while (true) {
            String input = br.readLine();

            if (input == null || input.equals("")) {
                break;
            }

            tree.add(input);
            insert(input.toLowerCase());

            index++;
        }

        List<String> treeList = new ArrayList(tree);
        Collections.sort(treeList);

        for (String t : treeList) {
            int number = available(t.toLowerCase());
            double percent = (double) (number * 100) / (double) index;

            sb.append(t + " " + String.format("%.4f", percent)).append("\n");
        }

        System.out.println(sb);
    }

    public static void insert(String str) {
        Trie currentTrie = root;

        for (int i = 0; i < str.length(); i++) {
            char target = str.charAt(i);

            if (!currentTrie.children.containsKey(target)) {
                currentTrie.children.put(target, new Trie());
            }

            currentTrie = currentTrie.children.get(target);
        }

        currentTrie.count += 1;
    }

    public static int available(String str) {
        Trie currentTrie = root;

        for (int i = 0; i < str.length(); i++) {
            char target = str.charAt(i);
            currentTrie = currentTrie.children.get(target);
        }

        return currentTrie.count;
    }
}

class Trie {
    Map<Character, Trie> children;
    int count;

    public Trie() {
        //space 는 26번째로
        this.children = new HashMap<>();
    }
}
