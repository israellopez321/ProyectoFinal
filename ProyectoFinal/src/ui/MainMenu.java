package ui;

import java.util.Scanner;
import model.character.Warrior;

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
	
	public void createCharacter() {
		System.out.println("Create your character! You can create up to 3 characters ");
		
		while (true) {
			System.out.println("Choose your class:");
			System.out.println("1. Warrior");
			System.out.println("2. Mage");
			System.out.println("3. Rogue");
			
			int classOption = sc.nextInt();
			
			if (classOption == 1) {
				System.out.println("You have chosen Warrior!");
				
				System.out.println("Introduce the name of your character:");
				String name = sc.next();
				
				Warrior warrior = new Warrior(name);
				break;
			} else if (classOption == 2) {
				System.out.println("You have chosen Mage!");
				break;
			} else if (classOption == 3) {
				System.out.println("You have chosen Rogue!");
				break;
			} else {
				System.out.println("Invalid option. Please try again.");
			}
		}
		
		
		System.out.println("Character created!");
	}
	
	
	
}
