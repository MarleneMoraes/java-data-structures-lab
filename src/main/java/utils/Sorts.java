package utils;

import java.util.Collections;
import java.util.List;

public class Sorts {
	
	// SELECTION SORT
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

	// INSERTION SORT
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
	
	// BUBBLE SORT
	public static <T extends Comparable<T>> void bubbleSort(List<T> list) {
		int n = list.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (list.get(j).compareTo(list.get(j + 1)) > 0) {
					Collections.swap(list, j, j + 1);
				}
			}
		}
	}

	// QUICK SORT
	public static <T extends Comparable<T>> void quickSort(List<T> list) {
		if (list != null && list.size() > 1) {
			performQuickSort(list, 0, list.size() - 1);
		}
	}

	private static <T extends Comparable<T>> void performQuickSort(List<T> list, int left, int right) {
		if (left < right) {
			int pivotIndex = partition(list, left, right);
			performQuickSort(list, left, pivotIndex - 1);
			performQuickSort(list, pivotIndex + 1, right);
		}
	}

	private static <T extends Comparable<T>> int partition(List<T> list, int left, int right) {
		T pivot = list.get(right);
		int i = left - 1;
		for (int j = left; j < right; j++) {
			if (list.get(j).compareTo(pivot) <= 0) {
				i++;
				Collections.swap(list, i, j);
			}
		}
		Collections.swap(list, i + 1, right);
		return i + 1;
	}
}