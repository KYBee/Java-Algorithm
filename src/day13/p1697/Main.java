package day13.p1697;

import java.util.*;
import java.io.*;

public class Main {

    static final int MAX_NUMBER = 100001;
    static int N, K;
    static Queue<Integer> queue = new LinkedList<>();
    static int[] visited = new int[MAX_NUMBER];

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day13/p1697/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        queue.add(N);
        visited[N] = 1;
        int current = 0;

        while (!queue.isEmpty()) {
            //1. 큐에서 뺌
            //2. 목적지인가?
            //3. 인접한 곳 검색
            //4. 갈수 있는가?
            //5. 체크인
            //6. 큐에 넣는다.
            current = queue.poll();

            if (current == K) {
                break;
            } else {

                // 인접한 곳 겁색과 갈 수 있는가?
                if (current - 1 >= 0 && visited[current - 1] == 0) {
                    queue.add(current - 1);
                    visited[current - 1] = visited[current] + 1;
                }

                if (current + 1 < MAX_NUMBER && visited[current + 1] == 0) {
                    queue.add(current + 1);
                    visited[current + 1] = visited[current] + 1;
                }

                if (current * 2 < MAX_NUMBER && visited[current * 2] == 0) {
                    queue.add(current * 2);
                    visited[current * 2] = visited[current] + 1;
                }
            }

        }

        System.out.println(visited[current] - 1);
    }
}
