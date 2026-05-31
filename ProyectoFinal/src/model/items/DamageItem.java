package model.items;

import model.character.Character;
import model.interfaces.Combatant;

/**
 * Class representing a damage-dealing item in the game. It extends the Item class and 
 * implements the use method to deal damage to a target.
 */
public class DamageItem extends Item{

	private int damageAmount;
	
	/**
	 * Constructor for DamageItem.
	 * @param id Unique identifier for the item.
	 * @param name Name of the item.
	 * @param description Description of the item's effect.
	 * @param cost Cost of the item in in-game currency.
	 * @param type Type of the item (e.g., "damage").
	 * @param damageAmount Amount of damage the item deals when used.
	 */
	public DamageItem(String id, String name, String description,int cost , String type, int damageAmount) {
		super(id, name, description, cost, type);
		this.damageAmount = damageAmount;
	}

	//Getter Damage Amount
	public int getDamageAmount() {
		return damageAmount;
	}
	
	@Override
	public void use(Character user, Combatant target) {
	    int dmg = target.takesDamage(damageAmount);
	    System.out.println(user.getName() + " uses " + name + " on " + target.getName() + " for " + dmg + " damage!");
	}
	
}
