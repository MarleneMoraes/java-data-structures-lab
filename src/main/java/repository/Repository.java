package repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
    
    public void selectionSort() {
        selectionSort(this.database);
    }
    
    public void selectionSort(List<T> list) {
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
}
