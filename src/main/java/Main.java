import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import model.Match;
import model.Player;
import model.TVShow;
import repository.MatchRepository;
import repository.PlayerRepository;
import repository.TVShowRepository;
import utils.StructureManager;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in, "UTF-8");

		System.out.println("Select the Domain to test:");
		System.out.println("1 - TV Shows (Series) | 2 - FIFA Matches | 3 - NBA Players");
		System.out.print("Option: ");

		String option = scan.nextLine();

		switch (option) {
		case "1":
			processTVShows(scan);
			break;
		case "2":
			processMatches(scan);
			break;
		case "3":
			processPlayers(scan);
			break;
		default:
			System.out.println("Invalid option.");
			break;
		}

		scan.close();
	}

	private static void processTVShows(Scanner scan) {
		TVShowRepository repository = new TVShowRepository();
		repository.loadFromFile("src/main/java/data/tv-series-data.txt", true);

		if (scan.hasNextLine()) {
			try {
				String input = scan.nextLine();
				while (!input.equals("FIM")) {
					TVShow found = repository.find(input);
					if (found != null) {
						repository.enqueueWithLimit(found, 20);
					}
					input = scan.nextLine();
				}

				if (scan.hasNextLine()) {
					int numCommands = Integer.parseInt(scan.nextLine());
					for (int i = 0; i < numCommands; i++) {
						String line = scan.nextLine();
						String[] parts = line.split(" ", 2);
						String action = parts[0];

						switch (action) {
						case "I":
							TVShow found = repository.find(parts[1]);
							if (found != null) {
								repository.enqueueWithLimit(found, 20);
							}
							break;
						case "R":
							repository.dequeue();
							break;
						}
					}
				}

				repository.showQueue();

			} catch (NumberFormatException e) {
				System.err.println("Error: The number of commands must be a valid integer.");
			} catch (IllegalArgumentException e) {
				System.err.println("Data error: " + e.getMessage());
			}
		}
	}

	private static void processMatches(Scanner scan) {
		MatchRepository repository = new MatchRepository();
		repository.loadFromFile("src/main/java/data/partidas.txt", false);

		String input = scan.nextLine();
		while (!input.equals("FIM")) {
			Match found = repository.find(input);
			if (found != null) {
				repository.enqueueWithLimit(found, 100);
			}
			input = scan.nextLine();
		}

		try {
			if (scan.hasNextLine()) {
				int n = Integer.parseInt(scan.nextLine().trim());
				for (int i = 0; i < n; i++) {
					String line = scan.nextLine();
					String[] parts = line.split(" ", 2);
					String command = parts[0];

					switch (command) {
					case "E":
						Match found = repository.find(parts[1]);
						if (found != null)
							repository.enqueueWithLimit(found, 100);
						break;
					case "D":
						repository.dequeue();
						break;
					}
				}
			}

			repository.showQueue();

		} catch (NumberFormatException e) {
			System.err.println("Error: The quantity must be a valid number.");
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
		}
	}

	private static void processPlayers(Scanner scan) {
		PlayerRepository repository = new PlayerRepository();
	    repository.loadFromFile("src/main/java/data/players-data.txt", true);

	    String input = scan.nextLine();
	    while (!input.equals("FIM")) {
	        Player found = repository.find(input);
	        if (found != null) {
	            repository.enqueueWithLimit(found, 5);
	        }
	        input = scan.nextLine();
	    }

	    try {
	        if (scan.hasNextLine()) {
	            int n = Integer.parseInt(scan.nextLine().trim());
	            for (int i = 0; i < n; i++) {
	                String line = scan.nextLine();
	                String[] parts = line.split(" ", 2);
	                String command = parts[0];

	                switch (command) {
	                    case "I":
	                        Player found = repository.find(parts[1]);
	                        if (found != null) {
	                            repository.enqueueWithLimit(found, 5);
	                        }
	                        break;
	                    case "R":
	                        repository.dequeue();
	                        break;
	                }
	            }
	        }
	        repository.showQueue();

	    } catch (NumberFormatException e) {
	        System.err.println("Error: The number of commands must be a valid integer.");
	    } catch (Exception e) {
	        System.err.println("Unexpected error: " + e.getMessage());
	    }
	}

}
