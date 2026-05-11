package model.enemies;

import model.interfaces.Combatant;
import model.interfaces.TakesDamage;

public class Goblin extends Enemy implements Combatant, TakesDamage {	
	
	public Goblin(String name) {
		super(name, 30, 30, 10 ,10, 10, 20, 3, 8);
	}
	
	@Override
	public void useSkill(Combatant target) {
		// Goblin's skill: Quick Slash - a fast attack that ignores some of the target's defense
		if (mana >= 5) {
			mana -= 5;
			int damage = Math.max(0, attack - (target.getDefense() / 2));
			attack += damage; // Temporary boost for the next attack

		}
	}

	@Override
	public int takeDamage(int damage) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
