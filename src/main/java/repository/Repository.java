package repository;

import java.io.File;
import java.util.Scanner;

public abstract class Repository<T> {
    protected Object[] database;
    protected int count;

    public Repository(int maxSize) {
        this.database = new Object[maxSize];
        this.count = 0;
    }

    public final void loadFromFile(String path, boolean skipHeader) {
        try (Scanner sc = new Scanner(new File(path), "UTF-8")) {
            if (skipHeader && sc.hasNextLine()) {
            	sc.nextLine();
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("FIM")) break;
                
                database[count++] = parseLine(line);
            }
        } catch (Exception e) {
            System.err.println("Erro no carregamento: " + e.getMessage());
        }
    }

    protected abstract T parseLine(String line);
    
    public abstract T find(String criteria);
}
