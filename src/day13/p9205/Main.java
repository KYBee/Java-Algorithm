package day13.p9205;

import java.util.*;
import java.io.*;

public class Main {

    static int T;
    static int store;
    static boolean[] visited;
    static Landmark[] destination;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/day13/p9205/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        int x, y;

        for (int i = 0; i < T; i++) {

            st = new StringTokenizer(br.readLine());
            store = Integer.parseInt(st.nextToken());

            destination = new Landmark[2 + store];
            visited = new boolean[2 + store];

            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            destination[0] = new Landmark(x, y, 0);

            for (int j = 0; j < store; j++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                destination[j + 1] = new Landmark(x, y, j + 1);
            }

            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            destination[1 + store] = new Landmark(x, y, 1 + store);

            //dfs
            //1. 체크인
            //2. 목적지인가?
            //3. 인접한 곳 체크
            //4. 갈 수 있는가?
            //5. 간다
            //6. 체크아웃

            dfs(destination[0]);

            if (visited[destination[store + 1].data] == true) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }
    }

    static void dfs(Landmark land) {

        visited[land.data] = true;

        if (land.data == destination.length - 1) {
            return;
        }

        for (int i = 0; i < destination.length; i++) {
            if (land.getDistance(destination[i]) <= 1000 && visited[destination[i].data] == false) {
                dfs(destination[i]);
            }
        }
    }
}

class Landmark {
    int x;
    int y;
    int data;

    public Landmark(int x, int y, int data) {
        this.x = x;
        this.y = y;
        this.data = data;
    }

    public int getDistance(Landmark o) {
        return Math.abs(o.x - this.x) + Math.abs(o.y - this.y);
    }

    @Override
    public String toString() {
        return "Landmark{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


}