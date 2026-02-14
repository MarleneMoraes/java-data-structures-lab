package repository;

import java.time.LocalDate;

import model.Match;

public final class MatchRepository extends Repository<Match> {

	public MatchRepository() {
		super();
	}

	@Override
	protected Match parseLine(String line) {
		if (line == null || line.trim().isEmpty()) {
			throw new IllegalArgumentException("Line cannot be null or empty");
		}

		String[] data = line.split("#");

		if (data.length < 9) {
			throw new IllegalArgumentException("Invalid match data format.");
		}

		try {
			return new Match(LocalDate.of(
					Integer.parseInt(data[0].trim()), // year
					Integer.parseInt(data[3].trim()), // month
					Integer.parseInt(data[2].trim())), // day
					data[1].trim(),
					data[4].trim(),
					data[7].trim(),
					Integer.parseInt(data[5].trim()),
					Integer.parseInt(data[6].trim()),
					data[8].trim()
			);
		} catch (Exception e) {
			throw new IllegalArgumentException("Error parsing Match data: " + e.getMessage());
		}
	}
	public void print(Match match) {
	    if (match != null) {
	        System.out.println(match.toString());
	    }
	}
	
	@Override
	public Match find(String match) {
		
		String[] matchData = match.split(";");
		if (matchData.length < 2) return null;
		
		String[] dateData = matchData[0].split("/");
		String teamMatch = matchData[1].trim();

		LocalDate dateCurrent = LocalDate.of(
				Integer.parseInt(dateData[2].trim()), // year
				Integer.parseInt(dateData[1].trim()), // month
				Integer.parseInt(dateData[0].trim())); //day

		for (Match matchCurrent : database) {
			if (matchCurrent.getDate().equals(dateCurrent) &&
				(matchCurrent.getHomeTeam().equalsIgnoreCase(teamMatch) || 
				 matchCurrent.getAwayTeam().equalsIgnoreCase(teamMatch))) {
				return matchCurrent;
			}			
		}
		
		return null;

	}
}
