package day02.p1713;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileInputStream;

public class Main {

    static List<Picture> pictures;
    static int[] studentList;
    static int N;
    static int C;
    static Picture[] people;

    //TODO : 로직 구성
    /**
     * 1. 사진 틀이 비어있음
     * 2. 추천 받은 학생의 사진은 무조건 개시되야 함
     * 3. 비어있는 공간이 없다면
     *     4. 추천 받은 횟수가 가장 적은 학생의 사진 삭제
     *          5. 가장 적은 학생이 여러개면 가장 오래된 사진 삭제
     * */

    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/day02/p1713/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        C = sc.nextInt();
        //사진틀
        pictures = new LinkedList<>();
        //추천 명단
        studentList = new int[C];

        for (int i = 0; i < C; i++) {
            studentList[i] = sc.nextInt();
        }

        for (int student: studentList) {
            Picture current = new Picture(student);
            int pictureIndex = pictures.indexOf(current);

            //사진판에 있는 경우
            if (pictureIndex != -1) {
                pictures.get(pictureIndex).count++;
            } else {
                //사진판에 없는 경우
                //틀이 가득 찬 경우
                if (pictures.size() == N) {
                    Collections.sort(pictures);
                    pictures.remove(0);
                }
                pictures.add(current);
            }
        }

        Collections.sort(pictures, Comparator.comparingInt(o -> o.studentId));

        for (Picture picture : pictures) {
            System.out.print(picture.studentId + " ");
        }
    }
}
class Picture implements Comparable<Picture> {
    static int totalCount = 0;
    int studentId;
    int count;
    int order;

    Picture(int studentId) {
        this.studentId = studentId;
        this.count = 1;
        totalCount += 1;
        this.order = totalCount;
    }

    @Override
    public boolean equals(Object obj) {
        if(((Picture)obj).studentId == studentId) return true;
        return false;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "studentId=" + studentId +
                ", count=" + count +
                ", order=" + order +
                '}';
    }

    @Override
    public int compareTo(Picture o) {

        int comp1 = Integer.compare(count, o.count);
        if (comp1 == 0) {
            return Integer.compare(order, o.order);
        } else {
            return comp1;
        }
    }
}
