package model.items;

import model.character.Character;
import model.interfaces.Combatant;

/**
 * Class representing an armor item in the game. It extends the Item class and 
 * implements the use method to equip the armor on the user.
 */
public class Armor extends Item {

	private int defenseAmount;
	
	/**
	 * Constructor for Armor.
	 * @param id Unique identifier for the item.
	 * @param name Name of the item.
	 * @param description Description of the item's effect.
	 * @param cost Cost of the item in in-game currency.
	 * @param type Type of the item (e.g., "armor").
	 * @param defenseAmount Amount of defense the armor provides when equipped.
	 */
	public Armor(String id, String name, String description, int cost, String type, int defenseAmount) {
		super(id, name, description, cost , type);
		this.defenseAmount = defenseAmount;
	}

	//GETTER
	public int getDefenseAmount() {
		return defenseAmount;
	}

	@Override
	public void use(Character user, Combatant target) {
	    user.setArmor(this);
	    System.out.println(user.getName() + " equips " + name + "!");
	}
	
}
