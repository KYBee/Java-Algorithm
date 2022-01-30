package day14.p5014;

import java.util.*;
import java.io.*;

public class Main {

    static int[] move;
    static int F, S, G, U, D;
    static int[] visited;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day14/p5014/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        visited = new int[F + 1];
        move = new int[] {U, - 1 * D};

        bfs();

        if (visited[G] != 0) {
            System.out.println(--visited[G]);
        } else {
            System.out.println("use the stairs");
        }

    }

    public static void bfs() {
        //1. 큐에서 빼옴
        //2. 목적지인가?
        //3. 주변 인접 노드
        //4. 갈 수 있는가?
        //5. 체크인
        //6. 큐에 넣는다.

        Queue<Integer> queue = new LinkedList<>();
        int current = S;
        queue.add(current);
        visited[current] = 1;

        while(!queue.isEmpty()) {
            current = queue.poll();

            if (current == G) {
                return;
            } else {
                for (int i = 0; i < 2; i++) {
                    int newNode = current + move[i];

                    if (newNode <= F && newNode > 0 && (visited[newNode] == 0)) {
                        visited[newNode] = visited[current] + 1;
                        queue.add(newNode);

                    }
                }
            }
        }
    }
}
