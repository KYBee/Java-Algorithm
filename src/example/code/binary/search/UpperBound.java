package example.code.binary.search;

public class UpperBound {

    public static int upperBound(int[] numList, int target) {

        int start = 0, end = numList.length;

        while (start < end) {

            int mid = (start + end) / 2;

            if (numList[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }
}
