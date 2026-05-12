package model.enemies;

import model.interfaces.Combatant;

public class Goblin extends Enemy implements Combatant {	
	
	public Goblin(String name) {
		super(name, 30, 30, 10 ,10, 10, 20, 3, 8);
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


	
	
}
