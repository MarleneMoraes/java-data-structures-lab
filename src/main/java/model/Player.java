package model;

public class Player implements Cloneable, Comparable<Player> {

	private int id;
    private String name;
    private double height;
    private double weight;
    private String university;
    private int birthYear;
    private String birthCity;
    private String birthState;

    public Player() { }

    public Player(int id, String name, double height, double weight, String university, int birthYear, String birthCity,
			String birthState) {
		this.id = id;
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.university = university;
		this.birthYear = birthYear;
		this.birthCity = birthCity;
		this.birthState = birthState;
	}

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getBirthCity() {
		return birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public String getBirthState() {
		return birthState;
	}
	
	public void setBirthState(String birthState) {
		this.birthState = birthState;
	}

	@Override
	public String toString() {
	    return String.format("[%d ## %s ## %s ## %s ## %s ## %s ## %s ## %s]",
	        id, name, formatDisplay(height), formatDisplay(weight), 
	        formatDisplay(birthYear), university, birthCity, 
	        birthState);
	}

	private String formatDisplay(Number value) {
	    if (value.doubleValue() == -1.0) {
	        return "no info";
	    }
	    
	    return (value instanceof Double) ? String.format("%.0f", value) : value.toString();
	}

    @Override
    public Player clone() throws CloneNotSupportedException {
        return (Player) super.clone();
    }

	@Override
	public int compareTo(Player other) {
		return this.getName().compareTo(other.getName());
	}
}