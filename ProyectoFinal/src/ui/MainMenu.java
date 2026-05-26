package ui;

import java.lang.invoke.MethodHandles.Lookup.ClassOption;
import java.util.ArrayList;
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
	
	public ArrayList<Character> createCharacter() {
		System.out.println("Create your character! You can create up to 3 characters ");
		
		ArrayList<Character> allies = new ArrayList<>();
		
		while (allies.size() < 3) {
			System.out.println("Choose your class:");
			System.out.println("1. Warrior");
			System.out.println("2. Mage");
			System.out.println("3. Rogue");
			System.out.println("4. Cleric");
			System.out.println("5. Paladin");
			System.out.println("6. Archer");
			
			try {
			int classOption = sc.nextInt();
			} catch(Exception e){
				System.out.println("Introduce a whole number. ");
			}
			
			switch(classOption) {
			
			
			
			
			}
			
		}
			
		System.out.println("Character created!");
	
		return allies;
	}
	
	
	
}
