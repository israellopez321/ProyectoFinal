package mechanics;

import java.util.ArrayList;
import model.enemies.Enemy;
import model.interfaces.Combatant;
import model.character.Character;

public class TurnManager {

	private ArrayList<Character> allies;
	private ArrayList<Enemy> enemies;
	private ArrayList<Combatant> turnOrder;
	private int turnIndex = 0;
	
	/**
	 * Constructor initializes the turn manager with lists of allies and enemies, and builds the turn order based on their speed.
	 * @param allies
	 * @param enemies
	 */
	public TurnManager(ArrayList<Character> allies, ArrayList<Enemy> enemies) {
		if(allies != null)this.allies = allies;
		if(enemies != null)this.enemies = enemies;
		buildTurnOrder();
	}
	
	/** 
	 * Default constructor initializes empty lists for allies, enemies, and turn order.
	 */
	public TurnManager() {
		this.allies = new ArrayList<Character>();
		this.enemies = new ArrayList<Enemy>();
		this.turnOrder = new ArrayList<Combatant>();
	}
	
	//Getters and setters
	
	public ArrayList<Character> getAllies() {
		return allies;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public ArrayList<Combatant> getTurnOrder() {
		return turnOrder;
	}

	public int getTurnIndex() {
		return turnIndex;
	}

	/**
	 * Resets the turn index to 0, so the next time combat
	 */
	public void resetTurnIndex() {
		turnIndex = 0;
	}
	
	/**
	 * Builds the turn order based on the speed of the combatants.
	 */ 
	public void buildTurnOrder() {
		turnOrder = new ArrayList<Combatant>();
		turnOrder.addAll(allies);
		turnOrder.addAll(enemies);
		turnOrder.sort((a, b) -> Integer.compare(b.getSpeed(), a.getSpeed()));
	}
	
	/**
	 * Removes all allies from the turn order and clears the allies list.
	 */
	public void removeAllies() {
		turnOrder.removeAll(allies);
		allies.clear();
	}
	
	/**
	 * Removes all enemies from the turn order and clears the enemies list.
	 */
	public void removeEnemies() {
		turnOrder.removeAll(enemies);
		enemies.clear();
	}
	
	/**
	 * Adds an ally to the allies list and rebuilds the turn order.
	 * @param ally
	 */
	public void addAlly(Character ally) {
		if(ally != null) {
			allies.add(ally);
			buildTurnOrder();
		} else {
			throw new IllegalArgumentException("Ally cannot be null");
		}	
	}
	
	/**
	 * Adds an enemy to the enemies list and rebuilds the turn order.
	 * @param enemy
	 */
	public void addEnemy(Enemy enemy) {
		if(enemy != null) {
			enemies.add(enemy);
			buildTurnOrder();
		} else {
			throw new IllegalArgumentException("Enemy cannot be null");
		}
	}
	
	
	
	
	
	
	
	
	
}
