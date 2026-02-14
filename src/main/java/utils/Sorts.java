package utils;

import java.util.Collections;
import java.util.List;

public class Sorts {

    public static <T extends Comparable<T>> void selectionSort(List<T> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).compareTo(list.get(smallest)) < 0) {
                    smallest = j;
                }
            }
            Collections.swap(list, i, smallest);
        }
    }

    public static <T extends Comparable<T>> void insertionSort(List<T> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            T tmp = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).compareTo(tmp) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, tmp);
        }
    }
}