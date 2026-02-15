package utils;

import java.util.Queue;

import model.TVShow;

public class StructureManager {
	public static void enqueueTVShow(Queue<TVShow> queue, TVShow show) {
	    if (queue.size() >= 20) {
	        TVShow removed = queue.poll();
	        System.out.println("(R) " + removed.getName());
	    }
	    
	    queue.add(show);
	    System.out.println(getAverageSeasons(queue));
	}

	public static int getAverageSeasons(Queue<TVShow> queue) {
		if (queue.isEmpty()) return 0;
		
		double average = queue.stream()
							  .mapToInt(TVShow::getSeasons)
							  .average().orElse(0.0);

		return (int) Math.round(average);
	}
}
