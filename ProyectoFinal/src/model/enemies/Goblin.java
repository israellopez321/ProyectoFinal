package model.enemies;

import model.interfaces.Combatant;

/**
 * The Goblin class represents a specific type of enemy in the game. It extends the Enemy class and implements the Combatant interface.
 * Goblins are known for their agility and quick attacks, making them a common adversary for players in the early stages of the game.
 */
public class Goblin extends Enemy implements Combatant {	
	
	/**
	 * Constructor for the Goblin class. It initializes the goblin's attributes 
	 * based on its level, including health points, attack power, defense, speed, 
	 * mana, and rewards for defeating it.
	 * @param name The name of the goblin.
	 * @param level The level of the goblin, which affects its attributes and rewards.
	 */
	public Goblin(String name, int level) {
		super(name, 20 + (level * 4), 4 + (level * 1), 5 + (level * 2), 2 + level, 7 + (level * 2), 5 + level, 6 + (level * 2));
	}
	
	@Override
	public int useSkill(Combatant target) {
		// Goblin's skill: Quick Slash 
		if (mana >= 5) {
			mana -= 5;
			
			int damage = Math.max(0, (int)(attack * 1.5)); // Temporary boost for the next attack
			int damageTaken = target.takesDamage(damage); 
			
			return damageTaken;
		}
		System.out.println("No tiene suficiente mana");
		return -1;		
	}
	
	/**
	 * Goblin's attack method, which calculates damage based on its attack 
	 * attribute and applies it to the target.
	 */
	public int takesHealing(int healing) {
		// TODO Auto-generated method stub
		return 0;
	}



	public String toString() {
		return "Goblin | name:" + name + " | hp: " + hp + "/" + hpMax + " | attack: " + attack + " | defense: "
				+ defense + "| speed: " + speed + "|";
	}

	
	
}
