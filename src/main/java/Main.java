import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Match;
import model.TVShow;
import repository.MatchRepository;
import repository.PlayerRepository;
import repository.TVShowRepository;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in, "UTF-8");

		System.out.println("Select the Domain to test:");
		System.out.println("1 - TV Shows (Series)");
		System.out.println("2 - FIFA Matches");
		System.out.println("3 - NBA Players");
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
				int qtdSearch = Integer.parseInt(scan.nextLine().trim());
				List<TVShow> foundShows = new ArrayList<>();

	            for (int i = 0; i < qtdSearch; i++) {
	                if (scan.hasNextLine()) {
	                    String nameToFind = scan.nextLine().trim();
	                    TVShow show = repository.find(nameToFind);
	                    if (show != null) {
	                        foundShows.add(show);
	                    }
	                }
	            }
	            
	            repository.selectionSort(foundShows);
	            
	            System.out.println("\n--- FIND TV SHOWS (Sorted by Name) ---");
	            for (TVShow show : foundShows) {
	                repository.print(show);
	            }

			} catch (NumberFormatException e) {
				System.err.println("Error: The first line must be a valid number.");
			} catch (IllegalArgumentException e) {
				System.err.println("Data error: " + e.getMessage());
			}

		}
	}

	private static void processMatches(Scanner scan) {
		MatchRepository repository = new MatchRepository(1000);
		repository.loadFromFile("src/main/java/data/matches-data.txt", false);
		
		if (scan.hasNextLine()) {
String qtdMatchSearch = scan.nextLine();

			try {
				int qtdMatches = Integer.parseInt(qtdMatchSearch);
				
				System.out.println("\n--- FIND MATCHES ---");
				for (int i = 0; i < qtdMatches; i++) {
					if (scan.hasNextLine()) {
						repository.print(
repository.find(scan.nextLine()));
			    }
}				
			} catch (NumberFormatException e) {
	            System.err.println("Error: The quantity must be a valid number.");
	        } catch (Exception e) {
	            System.err.println("Unexpected error: " + e.getMessage());
	        }
		}
	}
	
	private static void processPlayers(Scanner scan) {
		PlayerRepository repository = new PlayerRepository(4000);
		repository.loadFromFile("src/main/java/data/players-data.txt", true);
		

	    try {
	    	System.out.println("\n--- FIND NBA PLAYERS ---");
	        while (scan.hasNextLine()) {
	            String input = scan.nextLine().trim();
	            
	            if (input.equalsIgnoreCase("FIM")) {
	                break;
	            }
	            
	            if (!input.isEmpty()) {
	                repository.print(repository.find(input));
	            }
	        }
	    } catch (Exception e) {
	        System.err.println("Player Data Error: " + e.getMessage());
	    }
	}

}
