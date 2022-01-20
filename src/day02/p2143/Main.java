package day02.p2143;

import java.io.*;
import java.util.*;

public class Main {

    static long T;
    static int lenA;
    static int lenB;
    static long[] A;
    static long[] B;
    static long count = 0;

    static List<Long> subA;
    static List<Long> subB;

    public static void main(String[] args) throws IOException {

        //TODO
        /**
         * 1. 각 배열의 부분 합 구하기
         * 2. 각 부분합 정렬하기
         * 3. A 부분합의 앞 부분에 포인터 선언
         * 4. B 부분합의 뒷 부분에 포인터 선언
         * 5. 두 포인터의 합을 구함
         *     - 포인터의 합이 T보다 작으면 A 부분합의 포인터를 뒤로 옮김
         *     - 포인터의 합이 T보다 크면 B 부분합의 포인터를 앞으로 옮김
         * */

        System.setIn(new FileInputStream("src/day02/p2143/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        lenA = Integer.parseInt(st.nextToken());

        A = new long[lenA];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < lenA; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        lenB = Integer.parseInt(st.nextToken());

        B = new long[lenB];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < lenB; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }


        // 부분합 용 LinkedList 선언
        subA = new ArrayList<>();
        subB = new ArrayList<>();


        long subSum;
        // A 부분합을 저장하는 ArrayList
        for (int i = 0; i < lenA; i++) {
            subSum = A[i];
            subA.add(subSum);

            for (int j = i + 1; j < lenA ; j++) {
                subSum += A[j];
                subA.add(subSum);
            }
        }

        // B 부분합을 저장하는 ArrayList
        for (int i = 0; i < lenB; i++) {
            subSum = B[i];
            subB.add(subSum);

            for (int j = i + 1; j < lenB ; j++) {
                subSum += B[j];
                subB.add(subSum);
            }
        }


        Collections.sort(subA);
        Collections.sort(subB, Comparator.reverseOrder());


        long result = 0;
        int p1 = 0;
        int p2 = 0;

        while (p1 < subA.size() && p2 < subB.size()) {

            //5. 두 포인터의 합을 구함
            // - 포인터의 합이 T보다 작으면 A 부분합의 포인터를 뒤로 옮김
            // - 포인터의 합이 T보다 크면 B 부분합의 포인터를 앞으로 옮김

            long currentA = subA.get(p1);
            long target = T - currentA;

            //currentB == target -> subA, subB에 대해 같은 수 개수 체크 -> 답 구하기 -> 인덱스는 둘 다 올라감
            //currentB > target
            //currentB < target

            if(subB.get(p2) == target) {
                //같을떄는 개수를 새야 함
                long countA = 0;
                long countB = 0;

                while (p1 < subA.size() && subA.get(p1) == currentA) {
                    countA++;
                    p1++;
                }
                while (p2 < subB.size() && subB.get(p2) == target) {
                    countB++;
                    p2++;
                }
                count += countA * countB;

            } else if (subB.get(p2) > target) {
                p2++;
            } else {
                p1++;
            }
//
//            if (sum > T) {
//                // sum 보다 T 가 클 경우
//                p2--;
//            } else if (sum < T) {
//                // sum 보다 T 가 작을 경우
//                p1++;
//            } else {
//                // 같은 경우
//                int countA = 0;
//                int countB = 0;
//                long p1A = subA.get(p1);
//                long p2B = subB.get(p2);
//
//                for (int i = p1; i < subA.size(); i++) {
//                    if (subA.get(i) == p1A) {
//                        countA++;
//                    } else {
//                        break;
//                    }
//                }
//
//                for (int i = p2; i >= 0; i--) {
//                    if (subB.get(i) == p2B) {
//                        countB++;
//                    } else {
//                        break;
//                    }
//                }
//
//                p1 += countA;
//                p2 -= countB;
//
//                count += countA * countB;
//            }
//

        }


        System.out.println(count);

//        for (int num : subA) {
//            System.out.print(num + " ");
//        }
//        System.out.println();
//
//        for (int num : subB) {
//            System.out.print(num + " ");
//        }
//        System.out.println();
//        System.out.println(Arrays.toString(A));
//        System.out.println(Arrays.toString(B));

    }

}
