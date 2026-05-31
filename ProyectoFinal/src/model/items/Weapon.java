package model.items;

import model.character.Character;
import model.interfaces.Combatant;

/**
 * Class representing a weapon item in the game. It extends the Item class and 
 * adds an attack amount attribute.
 */
public class Weapon extends Item {

	private int attackAmount;
	
	
	/**
	 * Constructor Initializes the weapon with the given parameters.
	 * @param id
	 * @param name
	 * @param description
	 * @param cost
	 * @param type
	 * @param attackAmount
	 */
	public Weapon(String id, String name, String description, int cost, String type, int attackAmount) {
		super(id, name, description, cost , type);
		this.attackAmount = attackAmount;
	}

	//getter
	
	public int getAttackAmount() {
		return attackAmount;
	}

	@Override
	public void use(Character user, Combatant target) {
	    user.setWeapon(this);
	    System.out.println(user.getName() + " equips " + name + "!");
	}
}
