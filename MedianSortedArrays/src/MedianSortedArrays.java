/**
 * Bibhas Abhishek
 * bibhas_01@hotmail.com
 * 08 Apr 2018
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * https://github.com/bibhas-abhishek/projects/tree/master/MedianSortedArrays
 **/

public class MedianSortedArrays {

    public double findMedianSortedArrays(int[] array1, int[] array2) {
        // arr1.length < arr2.length -> assumption
        if (array1.length > array2.length)
            return findMedianSortedArrays(array2, array1);

        int x = array1.length;
        int y = array2.length;
        int low = 0;
        int high = x;
        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;
            // left.size >= right.size
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : array1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : array1[partitionX];
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : array2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : array2[partitionY];
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0)
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                else
                    return (double) Math.max(maxLeftX, maxLeftY);
            } else if (maxLeftX < minRightY)
                low = partitionX + 1;
            else
                high = partitionX - 1;
        }
        throw new IllegalArgumentException(); // array not sorted
    }

    public static void main(String[] args) {
        int[] x = {1, 2,};
        int[] y = {3, 4, 5};
        System.out.println(new MedianSortedArrays().findMedianSortedArrays(x, y));
    }

}
