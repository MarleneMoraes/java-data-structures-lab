package repository;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import model.Measurable;

public abstract class Repository<T extends Measurable & Comparable<T>> {
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
    
    protected Queue<T> queue = new ArrayDeque<>();
    
    public void enqueueWithLimit(T item, int limit) {
    	if (queue.size() >= limit) {
    		queue.poll(); // FIFO
    	}
    	queue.add(item);
    	System.out.println(calculateAverage()); 
    }
    
    public T dequeue() {
    	if (queue.isEmpty()) return null;
    	T removed = queue.poll();
    	System.out.print("(D) ");
    	System.out.println(removed);
    	return removed;
    }
    
    public int calculateAverage() {
    	if (queue.isEmpty()) return 0;
    	double avg = queue.stream()
    			.mapToInt(Measurable::getMeasurementValue)
    			.average()
    			.orElse(0.0);
    	return (int) Math.round(avg);
    }
    
    public void showQueue() {
    	int i = 0;
    	for (T item : queue) {
    		System.out.print("[" + i++ + "] ");
    		System.out.println(item);
    		
    	}
    }
}

