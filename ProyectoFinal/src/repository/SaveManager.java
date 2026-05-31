package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.character.Character;

/**
 * The SaveManager class is responsible for saving and loading the game state. 
 * It provides methods to save the current state of the player's party and 
 * the dungeon floor to a file, and to load that information back into the game 
 * when needed.
 */
public class SaveManager {

    private static final String SAVE_FILE = "savegame.txt";

    /**
	 * Saves the current game state to a file. It writes the dungeon floor and 
	 * the attributes of each character in the party to the file.
	 * @param party The list of characters in the player's party.
	 * @param dungeonFloor The current floor of the dungeon.
	 * @throws IOException If an I/O error occurs while writing to the file.
	 */
    public static void saveGame(List<Character> party, int dungeonFloor) throws IOException {
        File file = new File(SAVE_FILE);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("DUNGEON_FLOOR;" + dungeonFloor);
            writer.newLine();

            for (Character c : party) {
                String line = String.join(";",
                        c.getClass().getSimpleName(),
                        c.getName(),
                        String.valueOf(c.getHp()),
                        String.valueOf(c.getHpMax()),
                        String.valueOf(c.getMana()),
                        String.valueOf(c.getManaMax()),
                        String.valueOf(c.getAttack()),
                        String.valueOf(c.getDefense()),
                        String.valueOf(c.getSpeed()),
                        String.valueOf(c.getLevel()),
                        String.valueOf(c.getExperience()),
                        String.valueOf(c.getExperienceToNextLevel())
                );
                writer.write(line);
                writer.newLine();
            }
        }
    }

    /**
     * Loads the game state from a file. It reads the dungeon floor and 
     * the attributes of each character in the party from the file and returns 
     * them as a list of strings.
     * @return 
     * @throws IOException
     */
    public static List<String> loadGame() throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(SAVE_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }
}