package day09.p2108;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int total = 0;
    static int[] numList;
    static WordCount[] wordCounts;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day09/p2108/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        numList = new int[n];
        wordCounts = new WordCount[n];
        int index = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int currentNumber = Integer.parseInt(st.nextToken());
            total += currentNumber;
            numList[i] = currentNumber;
            WordCount w = isContain(currentNumber, index);

            if (w == null) {
                wordCounts[index++] = new WordCount(currentNumber);
            } else {
                w.count++;
            }
        }

        Arrays.sort(numList);
        Arrays.sort(wordCounts);

        double average = (double) total / (double) n;

        int averageInt = (int) Math.round(average);
        int median = numList[n / 2];
        int range = numList[n - 1] - numList[0];
        int mode = wordCounts[0].num;

        System.out.println(averageInt);
        System.out.println(median);
        System.out.println(mode);
        System.out.println(range);

    }

    public static WordCount isContain(int n, int index) {
        for (int i = 0; i <= index; i++) {
            if (wordCounts[i].num == n) {
                return wordCounts[i];
            }
        }
        return null;
    }
}

class WordCount implements Comparable<WordCount> {
    int num;
    int count;

    public WordCount(int num) {
        this.num = num;
        this.count = 0;
    }

    @Override
    public int compareTo(WordCount o) {
        return o.count - this.count;
    }
}