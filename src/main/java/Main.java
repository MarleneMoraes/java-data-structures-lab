import java.util.Scanner;

import model.Match;
import model.Player;
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
		TVShowRepository repository = new TVShowRepository(1000);
		repository.loadFromFile("src/main/java/data/tv-series-data.txt", true);
		
		if (scan.hasNextLine()) {
			String qtdTVShowSearch = scan.nextLine();

			try {
				int qtdSearch = Integer.parseInt(qtdTVShowSearch);

				System.out.println("\n--- FIND TV SHOWS ---");
				for (int i = 0; i < qtdSearch; i++) {
					if (scan.hasNextLine()) {
						repository.print(
								repository.find(scan.nextLine()));
					}
				}

			} catch (NumberFormatException e) {
				System.err.println("Error: The first line must be a valid number.");
			} catch (IllegalArgumentException e) {
				System.err.println("Data error: " + e.getMessage());
			}

		}
	}

	private static void processMatches(Scanner scan) {
		if (scan.hasNextLine()) {
			try {
				String qtdInput = scan.nextLine();
				int qtdMatches = Integer.parseInt(qtdInput);
				Match[] matches = new Match[qtdMatches];

				for (int i = 0; i < qtdMatches; i++) {
					if (scan.hasNextLine()) {
						String input = scan.nextLine();
						matches[i] = Match.read(input);
					}
				}

				System.out.println("\n--- WORLD CUP MATCHES ---");
				for (Match match : matches) {
					if (match != null) {
						match.print();
					}
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}
	
	private static void processPlayers(Scanner scan) {
	    if (scan.hasNextLine()) {
	        try {
	            int qtdPlayers = Integer.parseInt(scan.nextLine());
	            Player[] players = new Player[qtdPlayers];


	            for (int i = 0; i < qtdPlayers; i++) {
	                if (scan.hasNextLine()) {
	                    String input = scan.nextLine();
	                    players[i] = Player.read(input);
	                }
	            }

	            System.out.println("\n--- NBA PLAYERS LIST ---");
	            for (Player p : players) {
	                if (p != null) p.print();
	            }
	        } catch (Exception e) {
	            System.err.println("Player Data Error: " + e.getMessage());
	        }
	    }
	}

}
