package sortingvisualizer;

public class HeapSort {
    public static void sort(int[] array, SortingGUI gui) {
        int n = array.length;
        for (int i = n ; i >= 0; i--) {
            heapify(array, n, i, gui);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0, gui);
        }
    }
    private static void heapify(int[] array, int n, int i, SortingGUI gui) {
        int largest = i;
        int left = 2 * i + 1; 
        int right = 2 * i + 2; 

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapify(array, n, largest, gui);
        }

        gui.repaintGraph();
    }
}

