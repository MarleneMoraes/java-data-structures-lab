package model;

public class TVShow implements Cloneable, Comparable<TVShow> {
	private String name;
	private String format;
	private String duration;
	private String countryOfOrigin;
	private String originalLanguage;
	private String originalBroadcaster;
	private String startDate;
	private int seasons;
	private int episodes;

	public TVShow() { }

	public TVShow(String name, String format, String duration, String countryOfOrigin, String originalLanguage,
			String originalBroadcaster, String startDate, int seasons, int episodes) {
		this.name = name;
		this.format = format;
		this.duration = duration;
		this.countryOfOrigin = countryOfOrigin;
		this.originalLanguage = originalLanguage;
		this.originalBroadcaster = originalBroadcaster;
		this.startDate = startDate;
		this.seasons = seasons;
		this.episodes = episodes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalBroadcaster() {
		return originalBroadcaster;
	}

	public void setOriginalBroadcaster(String originalBroadcaster) {
		this.originalBroadcaster = originalBroadcaster;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getSeasons() {
		return seasons;
	}

	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}

	public int getEpisodes() {
		return episodes;
	}

	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

	@Override
	public TVShow clone() throws CloneNotSupportedException {
		return (TVShow) super.clone();
	}

	@Override
	public String toString() {
		return name + " ## " + format + " ## " + duration + " ## "
				+ countryOfOrigin + " ## " + originalLanguage + " ## "
				+ originalBroadcaster + " ## " + startDate + " ## " 
				+ seasons + " ## " + episodes;
	}

	@Override
    public int compareTo(TVShow other) {
		return this.name.compareTo(other.getName());
    }

}