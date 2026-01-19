package model;

import java.util.Optional;

public class Player implements Cloneable {

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
	
	private static String validateString(String value) {
        return Optional.ofNullable(value)
                       .filter(s -> !s.trim().isEmpty())
                       .orElse("no info");
    }

    private static int validateInt(String value) {
        return (value == null || value.trim().isEmpty()) ? -1 : Integer.parseInt(value.trim());
    }
    
    private static double validateDouble(String value) {
        return (value == null || value.trim().isEmpty()) ? -1.0 : Double.parseDouble(value.trim());
    }

	public static Player read(String line) {
        String[] data = line.split(",", -1);

        try {
            Player p = new Player();
            p.id = Integer.parseInt(data[0].trim());
            p.name = validateString(data[1]);
            p.height = validateDouble(data[2]);
            p.weight = validateDouble(data[3]);
            p.university = validateString(data[4]);
            p.birthYear = validateInt(data[5]);
            p.birthCity = validateString(data[6]);
            p.birthState = validateString(data.length > 7 ? data[7] : "");
            return p;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing Player: " + e.getMessage());
        }
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
    
    public void print() {
        System.out.println(this.toString());
    }
}