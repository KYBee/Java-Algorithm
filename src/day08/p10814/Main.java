package day08.p10814;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();
    static Member[] members;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day08/p10814/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        members = new Member[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            members[i] = new Member(age, name);
        }

        Arrays.sort(members);

        for (Member m : members) {
            sb.append(m.age).append(" ").append(m.name).append("\n");
        }

        System.out.println(sb.toString());
    }
}


class Member implements Comparable<Member> {

    int age;
    String name;
    static int enrollOrder;
    static int count = 0;

    public Member(int age, String name) {
        this.age = age;
        this.name = name;
        this.enrollOrder = count++;
    }

    @Override
    public int compareTo(Member o) {
        int comp = this.age - o.age;
        if (comp == 0) {
            return this.enrollOrder - o.enrollOrder;
        }
        return comp;
    }
}