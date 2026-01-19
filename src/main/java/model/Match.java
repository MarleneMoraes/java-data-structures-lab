package model;

import java.time.LocalDate;

public class Match implements Cloneable {
	private LocalDate date;
	private String stage;
	private String homeTeam;
	private String awayTeam;
	private int homeScore;
	private int awayScore;
	private String venue;

	public Match() { }

	public Match(LocalDate date, String stage, String homeTeam, String awayTeam, int homeScore, int awayScore,
			String venue) {
		super();
		this.date = date;
		this.stage = stage;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.venue = venue;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	@Override
	public Match clone() throws CloneNotSupportedException {
		return (Match) super.clone();
	}

	public static Match read(String line) {
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

	public void print() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return String.format(
				"[COPA %d] [%s] [%02d/%02d] [%s (%d) x (%d) %s] [%s]",
                date.getYear(), stage, date.getDayOfMonth(), date.getMonthValue(),
                homeTeam, homeScore, awayScore, awayTeam, venue);
	}
	
}