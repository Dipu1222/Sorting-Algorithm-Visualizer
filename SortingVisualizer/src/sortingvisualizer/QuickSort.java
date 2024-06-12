/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sortingvisualizer;
public class QuickSort {
    public static void sort(int[] array, SortingGUI gui) {
        quickSort(array, 0, array.length - 1, gui);
    }

    private static void quickSort(int[] array, int low, int high, SortingGUI gui) {
        if (low < high) {
            int pi = partition(array, low, high, gui);
            quickSort(array, low, pi - 1, gui);
            quickSort(array, pi + 1, high, gui);
        }
    }

    private static int partition(int[] array, int low, int high, SortingGUI gui) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                gui.repaintGraph();
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        gui.repaintGraph();
        return i + 1;
    }
}
