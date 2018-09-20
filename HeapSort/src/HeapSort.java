import java.util.Arrays;

/**
 * Bibhas Abhishek
 * bibhas_01@hotmail.com
 * 29 Jun 2018
 * https://github.com/bibhas-abhishek/projects/tree/master/HeapSort
 **/

public class HeapSort {

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private static void heapify(int[] array, int n, int i) { // heapify at index i
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && array[left] > array[largest])
            largest = left;

        if (right < n && array[right] > array[largest])
            largest = right;

        if (largest != i) {
            swap(array, i, largest);
            heapify(array, n, largest);
        }
    }

    private static void heapSort(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--)        // creating a maxHeap from last internal node = n/2 - 1
            heapify(array, n, i);

        for (int i = n - 1; i >= 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 1, 4, 2, 3, 6};
        heapSort(array);
        Arrays.stream(array).mapToObj(i -> i + " ").forEach(System.out::print);
    }

}
