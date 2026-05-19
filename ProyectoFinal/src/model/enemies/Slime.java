package model.enemies;

import model.interfaces.Combatant;

public class Slime extends Enemy{

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
	
}
