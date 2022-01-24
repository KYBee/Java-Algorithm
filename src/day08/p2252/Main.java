package day08.p2252;

import java.io.*;
import java.util.*;


public class Main {

    static int n, m;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] inDegree; // 각 정점의 진입 차수를 저장한 배열


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day08/p2252/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        //initialize
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<Integer>());
        }

        ArrayDeque<Integer> dq = new ArrayDeque<>();
        inDegree = new int[n + 1];

        int from, to;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            inDegree[to]++;
        }

        //최초 탐색할 학생을 찾아 큐에 넣는다.
        for (int i = 1; i <= n; i++) {
            if(inDegree[i] == 0) {
                dq.addLast(i);
            }
        }

        int seq = 0;
        while(dq.isEmpty() == false) {
            int now = dq.pollFirst();
            seq++;

            if(seq == n) {
                bw.write(String.valueOf(now));
            } else {
                bw.write(String.valueOf(now) + " ");
            }


            //1. 인접 노드 검사
            //2. 0보다 큰 정점들만 탐색
            //3. 진입차수를 하나씩 내리고, 0이면 큐에 넣는다.

            for (int next : graph.get(now)) {
                if(inDegree[next] > 0) {
                    inDegree[next]--;
                    if(inDegree[next] == 0) {
                        dq.addLast(next);
                    }
                }
            }
        }


        bw.flush();
        bw.close();
    }

}
