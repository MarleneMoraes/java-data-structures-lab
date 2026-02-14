package repository;

import model.TVShow;

public final class TVShowRepository extends Repository<TVShow> {

	public TVShowRepository() {
		super();
	}

	@Override
	protected TVShow parseLine(String line) {
		if (line == null || line.trim().isEmpty()) {
			throw new IllegalArgumentException("Line cannot be null or empty");
		}
		
		String[] array = line.split(";");
		
		if (array.length < 9) {
			throw new IllegalArgumentException("Invalid data format");
		}
		
		try {
			return new TVShow(array[0].trim(), 
					array[1].trim(), 
					array[2].trim(), 
					array[3].trim(), 
					array[4].trim(), 
					array[5].trim(), 
					array[6].trim(), 
					Integer.parseInt(array[7].trim()),
					Integer.parseInt(array[8].trim()));
			
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Error parsing numeric fields (seasons/episodes): " + e.getMessage());
		}
	}

	public void print(TVShow show) {
	    if (show != null) {
	        System.out.println(show.toString());
	    }
	}
	
	@Override
	public TVShow find(String name) {		
		for (TVShow tvShow : database) {
			if(tvShow != null && 
				tvShow.getName().equalsIgnoreCase(name))
		        return tvShow;
		 }
		return null;
	}

}
