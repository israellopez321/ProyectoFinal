package ui;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import mechanics.Dungeon;
import mechanics.TurnManager;
import model.character.Character;
import model.enemies.Enemy;
import model.interfaces.Combatant;
import model.inventory.Inventory;
import model.inventory.InventorySlot;
import model.items.DamageItem;
import model.items.HealItem;
import model.items.Item;
import mechanics.ItemRegistry;
import model.skills.Skill;

/**
 * The CombatMenu class manages the combat system in a turn-based RPG game. 
 * It handles the flow of combat between the player's characters (allies) 
 * and the enemies, allowing the player to choose actions for their characters 
 * and managing the turn order. The class also provides methods for selecting 
 * targets, using skills and items, and giving loot after combat.
 */
public class CombatMenu {

    private ArrayList<Character> allies;
    private ArrayList<Enemy> enemies;
    private TurnManager turnManager;
    private Scanner sc = new Scanner(System.in);
    private Random rand = new Random();
    private Inventory inventory = new Inventory();
    private int floor;

    /**
	 * Constructor initializes the combat menu with the given allies, 
	 * floor number, and inventory. It generates enemies based on the floor 
	 * and sets up the turn manager.
	 * @param allies
	 * @param floor
	 * @param inventory
	 */
    public CombatMenu(ArrayList<Character> allies, int floor, Inventory inventory) {
        this.allies = allies != null ? allies : new ArrayList<>();
        this.enemies = new Dungeon().generateFloors(floor);
        this.turnManager = new TurnManager(this.allies, this.enemies);
        this.inventory = inventory != null ? inventory : new Inventory();
        this.floor = floor;
    }

    /**
     * Starts the combat loop, allowing the player to take actions for 
     * their characters and the enemies to take their turns. 
     * The combat continues until either all enemies are defeated or all 
     * allies are defeated. After combat, it gives random loot if the player wins.
     * @return
     */
    public boolean startCombat() {
    	System.out.println("========== Floor " + floor + " ==========");
        System.out.println("========== Combat started ==========");
        printStatus();

        while (!turnManager.isCombatOver()) {
        	System.out.println("========== New Turn ==========");
            Combatant current = turnManager.nextCombatant();
            if (current == null) break;
            if (!current.isAlive()) continue;

            if (current instanceof Character) {
                handleAllyTurn((Character) current);
            } else if (current instanceof Enemy) {
                handleEnemyTurn((Enemy) current);
            }

            printStatus();
        }

        boolean anyEnemyAlive = enemies.stream().anyMatch(Enemy::isAlive);
        if (anyEnemyAlive) {
            System.out.println("You were defeated...");
            return false;
        } else {
            System.out.println("Victory! All enemies defeated.");
            giveRandomLoot(rand.nextInt(1,3)); // 1-3 random loot items
            return true;
        }
    }
    
    /**
	 * Handles the player's turn for a given character. 
	 * It prompts the player to choose an action (attack, use skill, defend, 
	 * or use item) and executes the chosen action accordingly. 
	 * The method also handles target selection for attacks and skills.
	 * @param actor
	 */
    private void handleAllyTurn(Character actor) {
        System.out.println("Turn: " + actor.getName() + " | HP: " + actor.getHp() + " | Mana: " + actor.getMana());
        System.out.println("Choose an action:");
        System.out.println("1) Attack");
        System.out.println("2) Use Skill");
        System.out.println("3) Defend");
        System.out.println("4) Use Item");

        int choice = readIntInRange(1, 4);

        switch (choice) {
            case 1:
                Enemy targetA = chooseEnemyTarget();
                if (targetA != null) {
                    int dmg = actor.attack(targetA);
                    System.out.println(actor.getName() + " attacks " + targetA.getName() + " for " + dmg + " damage.");
                }
                break;
            case 2:
                useSkillFlow(actor);
                break;
            case 3:
                actor.defend();
                System.out.println(actor.getName() + " is defending.");
                break;
            case 4:
                useItemFlow(actor);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    /**
     * Handles the flow for using a skill during the player's turn. 
     * It lists the available skills for the character, prompts the player 
     * to choose one, and then determines the appropriate target based on the 
     * skill type (healing or damaging). Finally, it executes the skill and 
     * displays the result.
     * @param actor
     */
    private void useSkillFlow(Character actor) {
        ArrayList<Skill> skills = actor.getSkills();
        if (skills == null || skills.isEmpty()) {
            System.out.println("No skills available.");
            return;
        }

        System.out.println("Choose a skill:");
        for (int i = 0; i < skills.size(); i++) {
            Skill s = skills.get(i);
            System.out.printf("%d) %s (Mana %d) - %s%n", i + 1, s.getName(), s.getManaCost(), s.getDescription());
        }
        int index = readIntInRange(1, skills.size()) - 1;
        Skill chosen = skills.get(index);

        // decide target list by skill type (simple heuristic)
        Combatant target;
        if (chosen.getType() != null && chosen.getType().toLowerCase().contains("heal")) {
            target = chooseAllyTarget();
        } else {
            target = chooseEnemyTarget();
        }

        if (target != null) {
            chosen.useSkill(actor, target);
            System.out.println(actor.getName() + " used " + chosen.getName() + " on " + (target.getName()));
        }
    }

    /**
     * Handles the enemy's turn by selecting a random alive ally as the target. 
     * The enemy will attempt to use a skill first, and if it fails 
     * (returns a negative result), it will perform a regular attack instead. 
     * The method also includes a brief pause after the enemy's action for better 
     * readability.
     * @param enemy
     */
    private void handleEnemyTurn(Enemy enemy) {
        System.out.println("\nEnemy Turn: " + enemy.getName());
        // try skill first if it does something; otherwise attack
        Character target = pickRandomAliveAlly();
        if (target == null) return;

        int result = enemy.useSkill(target);
        if (result < 0) {
            int dmg = enemy.attack(target);
            System.out.println(enemy.getName() + " attacks " + target.getName() + " for " + dmg + " damage.");
        } else {
            System.out.println(enemy.getName() + " used skill on " + target.getName() + " for " + result + " damage.");
        }
        
        waitSeconds(2);
    }

    /**
	 * Prompts the player to choose an enemy target from the list of alive enemies. It displays the available targets with their current HP and allows the player to select one by entering the corresponding number. If there are no alive enemies, it returns null.
	 * @return
	 */
    private Enemy chooseEnemyTarget() {
        ArrayList<Enemy> alive = new ArrayList<>();
        for (Enemy e : enemies) if (e.isAlive()) alive.add(e);
        if (alive.isEmpty()) return null;
        System.out.println("Choose enemy target:");
        for (int i = 0; i < alive.size(); i++) {
            Enemy e = alive.get(i);
            System.out.printf("%d) %s (HP %d)%n", i + 1, e.getName(), e.getHp());
        }
        int choice = readIntInRange(1, alive.size()) - 1;
        return alive.get(choice);
    }

    /**
     * Prompts the player to choose an ally target from the list of alive allies. 
     * It displays the available targets with their current HP and allows 
     * the player to select one by entering the corresponding number. 
     * If there are no alive allies, it returns null.
     * @return
     */
    private Character chooseAllyTarget() {
        ArrayList<Character> alive = new ArrayList<>();
        for (Character c : allies) if (c.isAlive()) alive.add(c);
        if (alive.isEmpty()) return null;
        System.out.println("Choose ally target:");
        for (int i = 0; i < alive.size(); i++) {
            Character c = alive.get(i);
            System.out.printf("%d) %s (HP %d)%n", i + 1, c.getName(), c.getHp());
        }
        int choice = readIntInRange(1, alive.size()) - 1;
        return alive.get(choice);
    }

    /**
     * Selects a random alive ally from the list of allies. It first creates a 
     * list of alive allies and then randomly picks one from that list. 
     * If there are no alive allies, it returns null.
     * @return
     */
    private Character pickRandomAliveAlly() {
        ArrayList<Character> alive = new ArrayList<>();
        for (Character c : allies) if (c.isAlive()) alive.add(c);
        if (alive.isEmpty()) return null;
        return alive.get(rand.nextInt(alive.size()));
    }

    /**
	 * Reads an integer input from the user and ensures it falls within a specified range. 
	 * It continuously prompts the user until a valid integer is entered. 
	 * If the input is not a valid integer or is out of the specified range, 
	 * it displays an error message and prompts again.
	 * @param min
	 * @param max
	 * @return
	 */
    private int readIntInRange(int min, int max) {
        int number = -1;
        while (true) {
            try {
                System.out.print("> ");
                number = Integer.parseInt(sc.next());
                if (number < min || number > max) {
                    System.out.println("Option out of range. Try again.");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    /**
	 * Prints the current status of all allies and enemies, including their names, 
	 * current HP, maximum HP, and mana (for allies). It organizes the output 
	 * into two sections: one for allies and one for enemies, making it easy for 
	 * the player to see the state of the combatants at a glance.
	 */
    private void printStatus() {
        System.out.println("\n-- Allies --");
        for (Character c : allies) {
            System.out.printf("%s | HP: %d/%d | Mana: %d/%d%n", c.getName(), c.getHp(), c.getHpMax(), c.getMana(),
                    c.getManaMax());
        }
        System.out.println("-- Enemies --");
        for (Enemy e : enemies) {
            System.out.printf("%s | HP: %d/%d%n", e.getName(), e.getHp(), e.getHpMax());
        }
    }
    
    /**
     * Handles the flow for using an item during the player's turn. 
     * It filters the inventory for consumable items (healing and damage items), 
     * prompts the player to choose one, and then determines the appropriate 
     * target based on the item type. Finally, it executes the item's effect 
     * on the chosen target and removes one quantity of the item from the inventory.
     * @param actor
     */
    private void useItemFlow(Character actor) {
        // Filter inventory for consumable items (HealItem and DamageItem)
        ArrayList<InventorySlot> consumableSlots = new ArrayList<>();
        for (InventorySlot slot : inventory.getItems()) {
            if (slot.getItem() instanceof HealItem || slot.getItem() instanceof DamageItem) {
                consumableSlots.add(slot);
            }
        }

        if (consumableSlots.isEmpty()) {
            System.out.println("No consumable items in inventory.");
            return;
        }

        System.out.println("Choose an item:");
        for (int i = 0; i < consumableSlots.size(); i++) {
            InventorySlot slot = consumableSlots.get(i);
            Item item = slot.getItem();
            System.out.printf("%d) %s x%d - %s%n", i + 1, item.getName(), slot.getQuantity(), item.getDescription());
        }

        int index = readIntInRange(1, consumableSlots.size()) - 1;
        InventorySlot selectedSlot = consumableSlots.get(index);
        Item item = selectedSlot.getItem();

        Combatant target;
        String type = item.getType().toLowerCase();
        if (type.contains("heal")) {
            target = chooseAllyTarget();
        } else {
            target = chooseEnemyTarget();
        }

        if (target == null) {
            System.out.println("No valid target.");
            return;
        }

        item.use(actor, target);
        System.out.println(actor.getName() + " used " + item.getName() + " on " + target.getName());
        inventory.remove(item);
    }
    
    /**
	 * Gives the player a specified number of random loot items after winning combat. 
	 * It retrieves all consumable items from the item registry, randomly selects 
	 * the specified number of items, adds them to the player's inventory, and 
	 * displays the names of the found items to the player.
	 * @param count
	 */
    private void giveRandomLoot(int count) {
        java.util.Collection<Item> consumables = ItemRegistry.getConsumableItems();

        ArrayList<Item> consumableList = new ArrayList<>(consumables);
        for (int i = 0; i < count; i++) {
            Item loot = consumableList.get(rand.nextInt(consumableList.size()));
            inventory.add(loot);
            System.out.println("You found: " + loot.getName());
        }
    }
    
    /**
	 * Utility method to pause the execution for a specified number of seconds. 
	 * It uses Thread.sleep to create a delay, and handles InterruptedException 
	 * by restoring the interrupted status of the thread.
	 * @param seconds
	 */
    private void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
}