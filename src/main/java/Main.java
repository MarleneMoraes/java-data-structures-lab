import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Match;
import model.Player;
import model.TVShow;
import repository.MatchRepository;
import repository.PlayerRepository;
import repository.TVShowRepository;

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
	            
	            if(foundShows.isEmpty()) {
					System.out.println("No TVShows were found with this name.");
				} else {					
					String algorithm = getSelectedAlgorithm(scan);
					repository.sort(foundShows, algorithm);
					
					System.out.println("\n--- FIND TV SHOWS (Sorted by Name) ---");
					for (TVShow show : foundShows) {
						repository.print(show);
					}
					
					generateLog(algorithm, utils.Sorts.lastComparisons, utils.Sorts.lastExecutionTime);
				}

			} catch (NumberFormatException e) {
				System.err.println("Error: The first line must be a valid number.");
			} catch (IllegalArgumentException e) {
				System.err.println("Data error: " + e.getMessage());
			}

		}
	}

	private static void processMatches(Scanner scan) {
		MatchRepository repository = new MatchRepository();
		repository.loadFromFile("src/main/java/data/matches-data.txt", false);
		
		if (scan.hasNextLine()) {

			try {
				int qtdMatches = Integer.parseInt(scan.nextLine().trim());
				
				List<Match> foundMatches = new ArrayList<>();

				for (int i = 0; i < qtdMatches; i++) {
					Match findMatch = repository.find(scan.nextLine().trim());
					
					if (findMatch != null) {
						foundMatches.add(findMatch);
					}
				}
				
				if(foundMatches.isEmpty()) {
					System.out.println("No matches were found with the provided criteria.");
				} else {
					String algorithm = getSelectedAlgorithm(scan);
					repository.sort(foundMatches, algorithm);
					
					System.out.println("\n--- FIFA MATCHES (Sorted by Selection/Date) ---");
					for (Match match : foundMatches) {
						repository.print(match);
					}
					
					generateLog(algorithm, utils.Sorts.lastComparisons, utils.Sorts.lastExecutionTime);
				}
				
			} catch (NumberFormatException e) {
	            System.err.println("Error: The quantity must be a valid number.");
	        } catch (Exception e) {
	        	System.err.println("Unexpected error: " + (e.getMessage() != null ? e.getMessage() : "Internal failure"));
	        }
		}
	}
	
	
	private static void processPlayers(Scanner scan) {
		PlayerRepository repository = new PlayerRepository();
		repository.loadFromFile("src/main/java/data/players-data.txt", true);
		

	    try {
	    	List<Player> foundPlayers = new ArrayList<>();
	    	
	        while (scan.hasNextLine()) {
	            String input = scan.nextLine().trim();
	            
	            if (input.equalsIgnoreCase("FIM")) {
	                break;
	            } else if (!input.isEmpty()) {
	            	Player findPlayer = repository.find(input);
	            	if (findPlayer != null) {
	            		foundPlayers.add(findPlayer);
	            	}
	            }
	        }
	        
	        if(foundPlayers.isEmpty()) {
				System.out.println("No players were found with this ids.");
			} else {
				String algorithm = getSelectedAlgorithm(scan);
				repository.sort(foundPlayers, algorithm);
				
				System.out.println("\n--- NBA PLAYERS (sorted by Name) ---");
				for (Player player : foundPlayers) {
					repository.print(player);
				}
				generateLog(algorithm, utils.Sorts.lastComparisons, utils.Sorts.lastExecutionTime);
			}
	    } catch (Exception e) {
	        System.err.println("Player Data Error: " + e.getMessage());
	    }
	}

	private static String getSelectedAlgorithm(Scanner scan) {
	    System.out.println("\nSelect Sorting Algorithm:");
	    System.out.println("1 - Selection | 2 - Insertion | 3 - Bubble | 4 - Quick | 5 - Merge | 6 - Heap");
	    System.out.print("Option: ");
	    
	    String sortOption = scan.nextLine().trim();
	    
	    switch (sortOption) {
	    	case "1": return "selection";
	        case "2": return "insertion";
	        case "3": return "bubble";
	        case "4": return "quick";
	        case "5": return "merge";
	        case "6": return "heap";
	        default:  return "";
	    }
	}
	
	private static void generateLog(String algorithm, int comparisons, long timeNano) {
	    String fileName = "log.txt";
	    try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(fileName, true))) {
	        double timeMs = timeNano / 1_000_000.0;
	        writer.printf("Algorithm: %s\tComparisons: %d\tTime: %.4fms%n", 
	                      algorithm.toUpperCase(), comparisons, timeMs);
	    } catch (java.io.IOException e) {
	        System.err.println("Error generating log file: " + e.getMessage());
	    }
	}
}
