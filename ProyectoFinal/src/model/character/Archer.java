package model.character;

import model.interfaces.Combatant;

public class Archer extends Character {

	/**
	 * Constructor for the Archer class, which initializes the character's attributes.
	 * @param name
	 */
	public Archer(String name) {
		super(name, 24, 6, 6, 8, 3, 4, 6);
	}
	
	@Override
	public void levelUp() {
		level++;
		experienceToNextLevel += 50;
		hpMax += 5;
		hp += 5;
		manaMax += 2;
		mana += 2;
		attack += 2;
		dexterity += 3;
		intelligence += 1;
		defense += 1;
		speed += 2;
	}

	@Override
	public int useSkill(Combatant target) {
		if (mana < 8) {
			return -1;
		}

		mana -= 8;
		int damage = attack + 6; // precise shot
		int applied = target.takesDamage(damage);
		return applied;
	}
	
}
