package model.items;

import model.character.Character;
import model.interfaces.Combatant;

/**
 * Abstract class representing an item in the game. It contains common attributes 
 * and methods for all items.
 */
public abstract class Item {
	
	protected String id;
	protected String name;
	protected String description;
	protected String type; // e.g., "heal", "damage", "buff", etc.
	protected int cost;
	
	/**
	 * Constructor for the Item class.
	 * @param id Unique identifier for the item.
	 * @param name Name of the item.
	 * @param description Description of the item's effect.
	 * @param cost Cost of the item in in-game currency.
	 * @param type Type of the item (e.g., "heal", "damage", "buff").
	 */
	public Item(String id, String name, String description, int cost, String type) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.cost = cost;
	}
	
	// Getters
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getType() {
		return type;
	}
	
	public int getCost() {
		return cost;
	}
	
	/**
	 * Abstract method that defines the behavior when using an item.
	 */
	public abstract void use(Character user, Combatant target);

}
