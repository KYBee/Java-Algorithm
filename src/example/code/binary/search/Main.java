package example.code.binary.search;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] numList;

    public static void main(String[] args) {

        N = 8;
        numList = new int[] {3, 3, 2, 2, 1, 4, 5, 6};

        Arrays.sort(numList);

        int up = UpperBound.upperBound(numList, 2);
        int down = LowerBound.lowerBound(numList, 2);

        System.out.println(Arrays.toString(numList));
        System.out.println(up);
        System.out.println(down);
        System.out.println(up - down);
    }
}
