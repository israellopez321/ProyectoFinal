package model.enemies;

import model.interfaces.Combatant;

/**
 * The Slime class represents a specific type of enemy in the game. It extends the 
 * Enemy class and implements the Combatant interface, providing specific attributes and behaviors for a slime enemy.
 */
public class Slime extends Enemy{
	
	/**
	 * Constructor for the Slime class. It initializes the slime's attributes based on its level.
	 * @param name The name of the slime.
	 * @param level The level of the slime, which affects its stats.
	 */
	public Slime(String name, int level) {
		super(name, 28 + (level * 6), 2 , 3 + level, 6 + (level * 2) , 2 , 3 + level, 5 + level);
	}

	@Override
	public int useSkill(Combatant target) {
		// Slime's skill: Sticky Goo 
		if (mana >= 3) {
			mana -= 3;
			
			int damage = Math.max(0, attack); // No boost, just a regular attack
			int damageTaken = target.takesDamage(damage); 
			
			return damageTaken;
		}
		System.out.println("No tiene suficiente mana");
		return -1;		
	}

	@Override
	public int takesHealing(int healing) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return "Slime | name:" + name + " | hp: " + hp + "/" + hpMax + " | attack: " + attack + " | defense: "
				+ defense + "| speed: " + speed + "|";
	}
	
}
