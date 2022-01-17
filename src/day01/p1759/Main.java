package day01.p1759;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static int L;
    public static int C;
    public static char[] data;
    public static List<String> result;

    /**
     * DFS 슈도코드
     * 1. 체크인
     * 2. 목적지인가?
     * 3. 연결된 곳 순회
     *      4. 갈 수 있는가?
     *      5. 간다
     * 6. 체크아웃
     * */

    /**
     * 매개변수는 사전정보가 아무것도 없다고 가정하고 특정 노드에 떨어졌을 때, 필요한 모든 것을 적어주어야 한다.
     * */
    public static void dfs(int length, int jaum, int moum, int current, String pwd) {

         // 1. 체크 인 - 생략 가능
         // 2. 목적지인가 - 암호 길이가 L까지 왔는가를 확인, 깊이가 L에 도달 했는지. + 자음, 모음 개수
         if (length == L) {
             if (jaum >= 2 && moum >= 1) {
                 result.add(pwd);
            }
         } else {
             // 3. 연결된 부부들 - 나보다 높은 알파벳
             for (int nextIndex = current + 1; nextIndex < data.length; nextIndex++) {
                 char nextData = data[nextIndex];
                 // 4. 갈 수 있는가? - 정렬을 해서 생략 가능
                 // 5. 간다 - dfs(next) - 재귀 -> 자음, 모음
                 if(nextData == 'a' || nextData == 'e' || nextData == 'i' || nextData == 'o' || nextData == 'u') {
                     dfs(length + 1, jaum, moum + 1, nextIndex, pwd + nextData);
                 } else {
                     dfs(length + 1, jaum + 1, moum, nextIndex, pwd + nextData);
                 }
             }
         }
         // 6. 체크아웃 - 생략 가능
    }

    public static void main(String[] args) throws Exception {

        //파일으로부터 내용을 읽어옴
        //System.setIn(new FileInputStream("src/day01/p1759/input.txt"));
        Scanner sc = new Scanner(System.in);

        L = sc.nextInt();
        C = sc.nextInt();

        data = new char[C];
        result = new LinkedList<>();

        for (int i = 0; i < C; i++) {
            data[i] = sc.next().charAt(0);
        }

        Arrays.sort(data);

//        // 모든 C개를 다 호출하는 경우
//        for (int i = 0; i < C; i++) {
//            if(data[i] == 'a' || data[i] == 'e' || data[i] == 'i' || data[i] == 'o' || data[i] == 'u') {
//                dfs( 1, 0, 1, i, String.valueOf(data[i]));
//            } else {
//                dfs(1, 1, 0, i, String.valueOf(data[i]));
//            }
//        }

        // 한 번의 DFS로 다 끝내는 경우
        dfs(0, 0, 0, -1, "");

        for (String password: result) {
            System.out.println(password);
        }
    }
}



