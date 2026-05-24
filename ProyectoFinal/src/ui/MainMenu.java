package ui;

import java.util.Scanner;

public class MainMenu {
	
	Scanner sc = new Scanner(System.in);
	
	public void display() {
	
		int option = 0;
		
		do {
		System.out.println("Welcome to the Game!");
		System.out.println("1. Start New Game");
		System.out.println("2. Load Game");
		System.out.println("3. Exit of game");
		
		try {
			option = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input. Please enter a number.");
			sc.next(); 
			continue; 
		}		
			switch (option) {
			case 1:
				System.out.println("Starting new game...");
				startNewGame();
				break;
			case 2:
				System.out.println("Loading game...");
				loadGame();
				break;
			case 3:
				System.out.println("Exiting game. Goodbye!");
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}while (option != 3);
	}
	
	
	public void startNewGame() {
		// Lógica para iniciar un nuevo juego
		System.out.println("New game started!");
	}
	
	public void loadGame() {
		// Lógica para cargar un juego guardado
		System.out.println("Game loaded!");
	}
	
	
}
