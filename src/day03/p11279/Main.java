package day03.p11279;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    //TODO
    /**
     * 1. 힙을 구성할 배열을 하나 만듬
     * 2. Insert 구현
     * 3. Delete 구현
     * */
    static maxHeap heap;
    static int[] numList;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day03/p11279/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        heap = new maxHeap(N);
        numList = new int[N];

        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        }

        for (int num : numList) {
            if (num == 0) {
                System.out.println(heap.delete());
            } else {
                heap.insert(num);
            }
        }

    }

}

class maxHeap {

    List<Integer> heap = new ArrayList<>();

    public maxHeap(int n) {
        this.heap.add(0);
    }

    public void insert(int num) {
        /**
         * 1. leaf 마지막에 삽입
         * 2. 부모와 비교 후 조건에 맞지 않으면 Swap
         * 3. 조건이 만족되거나 root 까지 반복
         * */

        heap.add(num);

        int currentIndex = heap.size() - 1;
        int parentIndex = currentIndex / 2;
        int current;
        int parent;
        int temp;

        while (currentIndex > 1) {
            current = heap.get(currentIndex);
            parent = heap.get(parentIndex);

            if (current > parent) {
                temp = parent;
                heap.set(parentIndex, current);
                heap.set(currentIndex, temp);

                currentIndex = parentIndex;
                parentIndex /= 2;
            } else {
                break;
            }
        }
    }

    public int delete() {

        /**
         * 1. Root 에 leaf 마지막 데이터 가져옴
         * 2. 자식과 비교 후 조건이 맞지 않으면 swap
         * 3. 조건이 만족되거나 leaf까지 반복
         * */

        if (heap.size() == 1) {
            return 0;
        }

        int top = heap.get(1);
        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        int currentIndex = 1;
        int leftChildIndex;
        int rightChildIndex;
        int temp;

        while (true) {
            leftChildIndex = currentIndex * 2;
            rightChildIndex = currentIndex * 2 + 1;

            //자식 존재 유무 확인
            if (leftChildIndex >= heap.size()) {
                break;
            }

            //왼쪽 자식 먼저 확인
            int maxValue = heap.get(leftChildIndex);
            int maxIndex = leftChildIndex;

            //오른쪽 자식 확인
            if (rightChildIndex < heap.size() && heap.get(rightChildIndex) > maxValue) {
                maxValue = heap.get(rightChildIndex);
                maxIndex = rightChildIndex;
            }

            if (heap.get(currentIndex) < maxValue) {
                temp = heap.get(currentIndex);
                heap.set(currentIndex, maxValue);
                heap.set(maxIndex, temp);

                currentIndex = maxIndex;
            } else {
                break;
            }
        }

        return top;
    }

}
