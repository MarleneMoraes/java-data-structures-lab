import java.util.Scanner;

import model.Match;
import model.TVShow;

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
		default:
			System.out.println("Invalid option.");
			break;
		}

		scan.close();
	}

	private static void processTVShows(Scanner scan) {
		if (scan.hasNextLine()) {
			String qtdTVShowInput = scan.nextLine();

			try {
				int qtdTVShow = Integer.parseInt(qtdTVShowInput);
				TVShow[] tvShows = new TVShow[qtdTVShow];

				if (scan.hasNextLine()) {
					scan.nextLine(); // skip header
				}

				for (int i = 0; i < qtdTVShow; i++) {
					if (scan.hasNextLine()) {
						String input = scan.nextLine();
						tvShows[i] = TVShow.read(input);
					}
				}

				System.out.println("\n--- REGISTERED TV SHOWS ---");
				for (TVShow tvShow : tvShows) {
					if (tvShow != null) {
						tvShow.print();
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

}
