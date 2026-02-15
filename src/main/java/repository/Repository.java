package repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.Sorts;

public abstract class Repository<T extends Comparable<T>> {
    protected List<T> database = new ArrayList<>();
    protected int count;

    public final void loadFromFile(String path, boolean skipHeader) {
        try (Scanner sc = new Scanner(new File(path), "UTF-8")) {
            if (skipHeader && sc.hasNextLine()) {
            	sc.nextLine();
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("FIM")) break;
                
                database.add(parseLine(line));
            }
        } catch (Exception e) {
            System.err.println("Erro no carregamento: " + e.getMessage());
        }
    }

    protected abstract T parseLine(String line);
    
    public abstract T find(String criteria);
    
    public void sort(List<T> list, String algorithm) {
        if (list == null || list.isEmpty()) return;

        switch (algorithm.toLowerCase()) {
            case "selection":
                Sorts.selectionSort(list);
                break;
            case "insertion":
                Sorts.insertionSort(list);
                break;
            case "bubble":
                Sorts.bubbleSort(list);
                break;
            case "quick": 
            	Sorts.quickSort(list); 
            	break;
            case "merge": 
            	Sorts.mergeSort(list); 
            	break;
            default:
                System.err.println("Algorithm not implemented.");
        }
    }
}
