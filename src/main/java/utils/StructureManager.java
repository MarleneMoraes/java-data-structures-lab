package utils;

import java.util.Queue;

import model.TVShow;

public class StructureManager {
	/**
     * Enqueues a TVShow and ensures the queue size does not exceed 20.
     * If the queue is full, the oldest item is removed.
     */
    public static void enqueueWithLimit(Queue<TVShow> queue, TVShow show) {
        if (queue.size() >= 20) {
            queue.poll();
        }
        queue.add(show);

        System.out.println(calculateAverageSeasons(queue));
    }

    /**
     * Calculates the rounded average of seasons for all shows in the queue.
     */
    public static int calculateAverageSeasons(Queue<TVShow> queue) {
        if (queue.isEmpty()) return 0;

        double average = queue.stream()
                .mapToInt(TVShow::getSeasons)
                .average()
                .orElse(0.0);

        return (int) Math.round(average); 
    }
}