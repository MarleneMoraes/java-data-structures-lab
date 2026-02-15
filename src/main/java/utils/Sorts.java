package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorts {
	
	public static long lastExecutionTime;
    public static int lastComparisons;

    // Log count
    private static long start() {
        lastComparisons = 0;
        return System.nanoTime();
    }

    private static void end(long startTime) {
        lastExecutionTime = System.nanoTime() - startTime;
    }

	// SELECTION SORT
	public static <T extends Comparable<T>> void selectionSort(List<T> list) {
		long time = start();
		int n = list.size();
		
		for (int i = 0; i < n - 1; i++) {
			int smallest = i;
			for (int j = i + 1; j < n; j++) {
				lastComparisons++;
				if (list.get(j).compareTo(list.get(smallest)) < 0) {
					smallest = j;
				}
			}
			Collections.swap(list, i, smallest);
		}
		end(time);
	}

	// INSERTION SORT
	public static <T extends Comparable<T>> void insertionSort(List<T> list) {
		long time = start();
		int n = list.size();
		
		for (int i = 1; i < n; i++) {
			T tmp = list.get(i);
			int j = i - 1;
			while (j >= 0) {
                lastComparisons++;
                if (list.get(j).compareTo(tmp) > 0) {
                    list.set(j + 1, list.get(j));
                    j--;
                } else break;
            }
			list.set(j + 1, tmp);
		}
		end(time);
	}

	// BUBBLE SORT
	public static <T extends Comparable<T>> void bubbleSort(List<T> list) {
		long time = start();
		int n = list.size();
		
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				lastComparisons++;
				if (list.get(j).compareTo(list.get(j + 1)) > 0) {
					Collections.swap(list, j, j + 1);
				}
			}
		}
		end(time);
	}

	// QUICK SORT
	public static <T extends Comparable<T>> void quickSort(List<T> list) {
		long time = start();
		
		if (list != null && list.size() > 1) {
			performQuickSort(list, 0, list.size() - 1);
		}
		
		end(time);
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
			lastComparisons++;
			if (list.get(j).compareTo(pivot) <= 0) {
				i++;
				Collections.swap(list, i, j);
			}
		}
		Collections.swap(list, i + 1, right);
		return i + 1;
	}

	// MERGE SORT
	public static <T extends Comparable<T>> void mergeSort(List<T> list) {
		long time = start();
		
		if (list.size() > 1) {
			List<T> sorted = performMergeSort(list);
			for (int i = 0; i < list.size(); i++) {
				list.set(i, sorted.get(i));
			}
		}
		
		end(time);
	}

	private static <T extends Comparable<T>> List<T> performMergeSort(List<T> list) {
		if (list.size() <= 1)
			return list;

		int mid = list.size() / 2;
		List<T> left = performMergeSort(new ArrayList<>(list.subList(0, mid)));
		List<T> right = performMergeSort(new ArrayList<>(list.subList(mid, list.size())));

		return merge(left, right);
	}

	private static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right) {
		List<T> combined = new ArrayList<>();
		int l = 0, r = 0;
		while (l < left.size() && r < right.size()) {
			lastComparisons++;
			if (left.get(l).compareTo(right.get(r)) <= 0) {
				combined.add(left.get(l++));
			} else {
				combined.add(right.get(r++));
			}
		}
		combined.addAll(left.subList(l, left.size()));
		combined.addAll(right.subList(r, right.size()));
		return combined;
	}
	
	// HEAP SORT
    public static <T extends Comparable<T>> void heapSort(List<T> list) {
    	long time = start();
		int n = list.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            Collections.swap(list, 0, i);
            heapify(list, i, 0);
        }
        
        end(time);
    }

    private static <T extends Comparable<T>> void heapify(List<T> list, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n) {
        	lastComparisons++;
            if (list.get(left).compareTo(list.get(largest)) > 0) largest = left;
        }

        if (right < n) {
        	lastComparisons++;
            if (list.get(right).compareTo(list.get(largest)) > 0) largest = right;
        }

        if (largest != i) {
            Collections.swap(list, i, largest);
            heapify(list, n, largest);
        }
    }
	
}