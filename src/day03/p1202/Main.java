package day03.p1202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int K;
    static List<Jewelry> jewelry = new ArrayList<>();
    static List<Integer> backpack = new ArrayList<>();
    static PriorityQueue<Jewelry> jewelryHeap = new PriorityQueue<>(Collections.reverseOrder());

    static long result = 0;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day03/p1202/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            jewelry.add(new Jewelry(weight, value));
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            backpack.add(Integer.parseInt(st.nextToken()));
        }


        //가방과 보석 정렬
        Collections.sort(backpack);
        Collections.sort(jewelry, new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                int comp = o1.weight - o2.weight;
                if(comp == 0) {
                    comp = o1.value - o2.value;
                }
                return comp;
            }
        });

        //Debugging
//        for (int i : backpack) {
//            System.out.println(i);
//        }
//        for (Jewelry j : jewelry) {
//            System.out.println(j.toString());
//        }

        int jewelryIndex = 0;
        for (int i : backpack) {

            //TODO
            /**
             * 1. 만약 가방에 들어갈 수 있는 무게라면 우선순위 큐에 넣음
             * 2. 가방에 들어갈 수 있는 모든 보석을 우선순위 큐에 넣은 뒤
             * 3. 우선 순위 큐에서 가장 높은 값을 제거함
             * */

            while (jewelryIndex < jewelry.size()) {
                // 가방에 들어갈 수 있는 무게면 우선순위 큐에 넣는음
                if (i >= jewelry.get(jewelryIndex).weight) {
                    jewelryHeap.offer(jewelry.get(jewelryIndex));
                    jewelryIndex++;
                } else {
                    break;
                }
            }

            // 우선 순위 큐에서 가장 높은 값을 제거함
            if (jewelryHeap.isEmpty()) {
                continue;
            } else {
                Jewelry target = jewelryHeap.poll();
                result += target.value;
            }
        }

        System.out.println(result);
    }

}


class Jewelry implements Comparable<Jewelry> {

    int value;
    int weight;

    Jewelry(int weight, int value) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public int compareTo(Jewelry o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Jewelry{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }
}