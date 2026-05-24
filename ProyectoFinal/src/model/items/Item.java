package model.items;

import model.character.Character;

public abstract class Item {
	
	protected String id;
	protected String name;
	protected String description;
	protected String type; // e.g., "heal", "damage", "buff", etc.
	protected int cost;
	
	
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
	public abstract void use(Character user);

}
