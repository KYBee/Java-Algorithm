package example.code.binary.search;

public class LowerBound {

    public static int lowerBound(int[] numList, int target) {

        int start = 0, end = numList.length;

        // start 와 end가 같을 때가 존재할 수 있음. 우리가 찾는 수가 numList에 없을 경우
        while (start < end) {

            int mid = (start + end) / 2;

            if (target <= numList[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}
