
package sortingvisualizer;

public class MergeSort {
    public static void sort(int[] array, SortingGUI gui) {
        mergeSort(array, 0, array.length - 1, gui);
    }

    private static void mergeSort(int[] array, int left, int right, SortingGUI gui) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid, gui);
            mergeSort(array, mid + 1, right, gui);
            merge(array, left, mid, right, gui);
        }
    }

    private static void merge(int[] array, int left, int mid, int right, SortingGUI gui) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
            gui.repaintGraph();
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
            gui.repaintGraph();
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
            gui.repaintGraph();
        }
    }
}
