package model.enemies;

import model.interfaces.Combatant;

public class Goblin extends Enemy implements Combatant {	
	
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
	
	public int takesHealing(int healing) {
		// TODO Auto-generated method stub
		return 0;
	}



	public String toString() {
		return "Goblin | name:" + name + " | hp: " + hp + "/" + hpMax + " | attack: " + attack + " | defense: "
				+ defense + "| speed: " + speed + "|";
	}

	
	
}
