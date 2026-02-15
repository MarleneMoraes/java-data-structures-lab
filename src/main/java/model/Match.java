package model;

import java.time.LocalDate;

public class Match implements Cloneable, Comparable<Match>, Measurable {
	private LocalDate date;
	private String stage;
	private String homeTeam;
	private String awayTeam;
	private int homeScore;
	private int awayScore;
	private String venue;

	public Match() {
	}

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

	@Override
	public String toString() {
		return String.format("[COPA %d] [%s] [%02d/%02d] [%s (%d) x (%d) %s] [%s]", date.getYear(), stage,
				date.getDayOfMonth(), date.getMonthValue(), homeTeam, homeScore, awayScore, awayTeam, venue);
	}

	@Override
	public int compareTo(Match other) {
		int result = this.homeTeam.compareTo(other.getHomeTeam());
		if (result != 0)
			return result;

		if (this.date.getYear() != other.date.getYear()) {
			return Integer.compare(this.date.getYear(), other.date.getYear());
		}

		if (this.date.getDayOfMonth() != other.date.getDayOfMonth()) {
			return Integer.compare(this.date.getDayOfMonth(), other.date.getDayOfMonth());
		}

		return Integer.compare(this.date.getMonthValue(), other.date.getMonthValue());
	}

	@Override
	public int getMeasurementValue() {
		return this.homeScore + this.awayScore;
	}

}