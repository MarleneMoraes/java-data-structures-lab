package repository;

import java.util.Optional;

import model.Player;

public final class PlayerRepository extends Repository<Player> {

	public PlayerRepository() {
		super();
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


	@Override
	protected Player parseLine(String line) {
		String[] data = line.split(",", -1);

		try {
			return new Player(Integer.parseInt(data[0].trim()), // id
					validateString(data[1]), // name
					validateDouble(data[2]), // height
					validateDouble(data[3]), // weight
					validateString(data[4]), // university
					validateInt(data[5]), 	// birthYear
					validateString(data[6]), // birthCity
					validateString(data.length > 7 ? data[7] : "") // birthState
			);
		} catch (Exception e) {
			throw new IllegalArgumentException("Error parsing Player: " + e.getMessage());
		}
	}

	public void print(Player player) {
		if (player != null) {
			System.out.println(player.toString());
		}
	}
	
	@Override
	public Player find(String input) {
		int id = Integer.parseInt(input.trim());

		for (Player player : database) {

			if (player != null && 
					player.getId() == id) {
				return player;
			}
		}

		return null;

	}
}
