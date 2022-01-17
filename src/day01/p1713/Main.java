package day01.p1713;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Item item1 = new Item(1, 2, 3);
        Item item2 = new Item(2, 3, 1);
        Item item3 = new Item(3, 1, 2);

        List<Item> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);

        System.out.println(list);

        // Comparable implement -> Collections의 default 정렬 방법
        Collections.sort(list);
        System.out.println(list);

        // Comparator 사용 -> Comparable 은 무시되고 Comparator이 사용됨
        Collections.sort(list, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return Integer.compare(o1.c, o2.c);
            }
        });
        System.out.println(list);


        // 실제 현업에서 사용하는 정렬 방식
        Collections.sort(list, Comparator.comparingInt(Item::getB));
        //Collections.sort(list, Comparator.comparingInt(Item::getB).reversed());
        //Collections.sort(list, Comparator.comparingInt(Item::getB).reversed().thenComparingInt(Item::getA));
        System.out.println(list);

    }
}

class Item implements Comparable<Item>{
    int a;
    int b;
    int c;

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public Item(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    // 음수 : 순서 유지
    // 0 : 순서 유지
    // 양수 : Swap
    @Override
    public int compareTo(Item o) {

        int comp1 = Integer.compare(o.a, a);
        if (comp1 == 0) {
            return Integer.compare(o.b, b);
        } else {
            return comp1;
        }

    }

}