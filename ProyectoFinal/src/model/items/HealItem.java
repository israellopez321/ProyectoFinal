package model.items;

import model.character.Character;
import model.interfaces.Combatant;

/**
 * Class representing a healing item in the game. It extends the Item class and 
 * implements the use method to heal the user.
 */
public class HealItem extends Item{

	private int healAmount;
	
	/**
	 * Constructor for HealItem.
	 * @param id Unique identifier for the item.
	 * @param name Name of the item.
	 * @param description Description of the item's effect.
	 * @param cost Cost of the item in in-game currency.
	 * @param type Type of the item (e.g., "heal").
	 * @param healAmount Amount of HP the item heals when used.
	 */
	public HealItem(String id, String name, String description, int cost, String type, int healAmount) {
		super(id, name, description, cost , type);
		this.healAmount = healAmount;
	}

	//GETTER
	
	public int getHealAmount() {
		return healAmount;
	}
	
	@Override
	public void use(Character user, Combatant target) {
	    int healed = user.takesHealing(healAmount);
	    System.out.println(user.getName() + " uses " + name + " and heals for " + healed + " HP!");
	}
	
	
	
}
