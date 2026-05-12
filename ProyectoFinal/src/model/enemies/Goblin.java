package model.enemies;

import model.interfaces.Combatant;

public class Goblin extends Enemy implements Combatant {	
	
	public Goblin(String name) {
		super(name, 30, 30, 10 ,10, 10, 20, 3, 8);
	}
	
	@Override
	public int useSkill(Combatant target) {
		// Goblin's skill: Quick Slash - a fast attack that ignores some of the target's defense
		if (mana >= 5) {
			mana -= 5;
			int damage = Math.max(0, (int)(attack * 0.5));
			int damageTaken = takesDamage(damage); // Temporary boost for the next attack
			System.out.println(name + " usa ataque furtivo y hace " + damage + " de daño");
			return damageTaken;
		}
		System.out.println("No tiene suficiente mana");
		return 0;
		
	}


	
	
}
