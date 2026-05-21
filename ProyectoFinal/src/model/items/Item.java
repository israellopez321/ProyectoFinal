package model.items;

public abstract class Item {
	
	protected String id;
	protected String name;
	protected String description;
	protected String type; // e.g., "heal", "damage", "buff", etc.
	
	public Item(String id, String name, String description, String type) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
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
	
	/**
	 * Abstract method that defines the behavior when using an item.
	 */
	public abstract void use();
	
	

}
