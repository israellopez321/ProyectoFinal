package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.character.Archer;
import model.character.Character;
import model.character.Cleric;
import model.character.Mage;
import model.character.Paladin;
import model.character.Rogue;
import model.character.Warrior;
import repository.SaveManager;
import model.inventory.Inventory;

public class MainMenu {

    Scanner sc = new Scanner(System.in);
    private int currentFloor = 1;
    private ArrayList<Character> party = new ArrayList<>();
    private Inventory inventory = new Inventory();

    /**
	 * Displays the main menu and handles user input for starting a new game, 
	 * loading a game, or exiting. It also manages the flow of the game by 
	 * allowing the player to enter the dungeon after creating or loading a party.
	 */
    public void display() {

        int option = 0;

        do {
            System.out.println("==============================");
            System.out.println("Welcome to the Game!");
            System.out.println("==============================");
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
                if (!party.isEmpty()) {
                    enterDungeon();
                }
                break;
            case 2:
                System.out.println("Loading game...");
                loadGame();
                if (!party.isEmpty() && currentFloor < 7) {
                    enterDungeon();
                } else {
                	System.out.println("===============================");
                	System.out.println("You have already completed the dungeon or no game loaded. Starting a new game...");
                	System.out.println("===============================");
                	startNewGame();
				}
                break;
            case 3:
                System.out.println("Exiting game. Goodbye!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
            }
        } while (option != 3);
    }

    /**
     * Starts a new game by creating a new party of characters, 
     * resetting the current floor to 1. It also initializes a new inventory 
     * and saves the game state.
     */
    public void startNewGame() {
        party = createCharacter();
        currentFloor = 1;
        inventory = new Inventory();

        if (!party.isEmpty()) {
            try {
                SaveManager.saveGame(party, currentFloor);
            } catch (IOException e) {
                System.out.println("Error al guardar: " + e.getMessage());
            }
            System.out.println("New game started!");
        } else {
            System.out.println("No characters created. New game canceled.");
        }
    }

    /**
	 * Loads a saved game by reading the game state from a file. It parses the 
	 * saved data to reconstruct the party of characters and the current floor. 
	 * If the save file is malformed or an error occurs during loading, it 
	 * displays an error message.
	 */
    public void loadGame() {
        try {
            List<String> lines = SaveManager.loadGame();
            party = new ArrayList<>();

            for (String line : lines) {
                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split(";", -1);

                if (parts[0].equals("DUNGEON_FLOOR")) {
                    currentFloor = Integer.parseInt(parts[1]);
                    continue;
                }

                if (parts.length < 12) {
                    throw new IOException("Save file malformed: " + line);
                }

                Character character = createCharacterFromType(parts[0], parts[1]);
                character.setHp(Integer.parseInt(parts[2]));
                character.setHpMax(Integer.parseInt(parts[3]));
                character.setMana(Integer.parseInt(parts[4]));
                character.setManaMax(Integer.parseInt(parts[5]));
                character.setAttack(Integer.parseInt(parts[6]));
                character.setDefense(Integer.parseInt(parts[7]));
                character.setSpeed(Integer.parseInt(parts[8]));
                character.setLevel(Integer.parseInt(parts[9]));
                character.setExperience(Integer.parseInt(parts[10]));
                character.setExperienceToNextLevel(Integer.parseInt(parts[11]));

                party.add(character);
            }

            System.out.println("Game loaded!");
            System.out.println("Current dungeon floor: " + currentFloor);
            System.out.println("Party:");
            for (Character c : party) {
                System.out.println(c);
            }
        } catch (IOException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }

    /**
	 * Creates a character instance based on the provided type and name. 
	 * It uses a switch expression to determine which character class 
	 * to instantiate.
	 * @param type The type of character to create (e.g., "Warrior", "Mage").
	 * @param name The name of the character.
	 * @return A new instance of the specified character type.
	 * @throws IllegalArgumentException If the provided character type is unknown.
	 */
    private Character createCharacterFromType(String type, String name) {
        return switch (type) {
        case "Warrior" -> new Warrior(name);
        case "Mage" -> new Mage(name);
        case "Rogue" -> new Rogue(name);
        case "Cleric" -> new Cleric(name);
        case "Paladin" -> new Paladin(name);
        case "Archer" -> new Archer(name);
        default -> throw new IllegalArgumentException("Unknown character type: " + type);
        };
    }

    /**
     * Allows the player to create up to 3 characters by selecting a class
     * @return
     */
    public ArrayList<Character> createCharacter() {
        System.out.println("Create your character! You can create up to 3 characters ");

        ArrayList<Character> allies = new ArrayList<>();
        int classOption = 0;

        while (allies.size() < 3 && classOption != 7) {

            String name = "";

            System.out.println("Choose your class:");
            System.out.println("1. Warrior");
            System.out.println("2. Mage");
            System.out.println("3. Rogue");
            System.out.println("4. Cleric");
            System.out.println("5. Paladin");
            System.out.println("6. Archer");
            System.out.println("7. Finish creating characters");

            try {
                classOption = sc.nextInt();
                System.out.print("Introduce the name of your character: ");
                name = sc.next();

            } catch (InputMismatchException e) {
                System.out.println("Introduce a whole number. ");
                sc.nextLine();
            }

            switch (classOption) {

            case 1:
                allies.add(new Warrior(name));
                System.out.println("Warrior " + name + " created!");
                break;

            case 2:
                allies.add(new Mage(name));
                System.out.println("Mage " + name + " created!");
                break;

            case 3:
                allies.add(new Rogue(name));
                System.out.println("Rogue " + name + " created!");
                break;

            case 4:
                allies.add(new Cleric(name));
                System.out.println("Cleric " + name + " created!");
                break;

            case 5:
                allies.add(new Paladin(name));
                System.out.println("Paladin " + name + " created!");
                break;

            case 6:
                allies.add(new Archer(name));
                System.out.println("Archer " + name + " created!");
                break;

            case 7:
                System.out.println("Finished creating characters.");
                break;

            default:
                System.out.println("Invalid class option. Please try again.");

            }

        }

        System.out.println("Character created!");

        return allies;
    }

    /**
     * Manages the flow of the game as the player progresses through 
     * the dungeon floors.
     */
    private void enterDungeon() {
        boolean continueDungeon = true;

        while (continueDungeon) {
            boolean victory = new CombatMenu(party, currentFloor,inventory).startCombat();
            if (!victory) {
                System.out.println("You have been defeated on the floor " + currentFloor);
                break;
            }
            
            int option = 0;
            
            System.out.println("You have won the floor " + currentFloor + "!");
            System.out.println("Do you want to go up to the next floor?");
            System.out.println("1) Yes");
            System.out.println("2) No");
            
            try {
            option = sc.nextInt();
            } catch (InputMismatchException e) {
				System.out.println("Introduce a number valid.");
				sc.nextLine();
				continue;
			}
            
            if (option == 1) {
                currentFloor++;
                for (Character c : party) {
					c.levelUp();
				}
                
                try {
                    SaveManager.saveGame(party, currentFloor);
                } catch (IOException e) {
                    System.out.println("Error of saved: " + e.getMessage());
                }
            } else {
                continueDungeon = false;
                loadGame();
            }

            if (currentFloor >= 7) {
                System.out.println("You have reached the end of the dungeon!");
                break;
            }
        }
    }
    
}