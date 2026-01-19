import java.util.Scanner;

import model.TVShow;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in, "UTF-8");
		
		if(scan.hasNextLine()) {
			String qtdTVShowInput = scan.nextLine();
			
			try {
				int qtdTVShow = Integer.parseInt(qtdTVShowInput);
				TVShow[] tvShows = new TVShow[qtdTVShow];
				
				if (scan.hasNextLine()) {
                    scan.nextLine(); // skip header
                }
				
				for (int i = 0; i < qtdTVShow; i++) {
					if(scan.hasNextLine()) {
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
		
		scan.close();
	}

}
